/*
 * Copyright (c) 2017 Fondesa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fondesa.lyra.field;

import com.fondesa.lyra.common.TestModels;
import com.fondesa.lyra.sharedtest.FieldMatcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Unit test class for {@link DefaultFieldsRetriever}.
 */
@RunWith(RobolectricTestRunner.class)
public class DefaultFieldsRetrieverTest {

    private DefaultFieldsRetriever mRetriever;

    @Before
    public void setUp() {
        mRetriever = new DefaultFieldsRetriever();
    }

    @Test
    public void testRetrieveZeroFields() {
        Field[] fields = mRetriever.getFields(TestModels.ZeroFields.class);
        assertNotNull(fields);
        assertEquals(fields.length, 0);
    }

    @Test
    public void testRetrieveZeroAnnotatedFields() {
        Field[] fields = mRetriever.getFields(TestModels.ZeroAnnotatedFields.class);
        assertNotNull(fields);
        assertEquals(fields.length, 0);
    }

    @Test
    public void testRetrievePublicAnnotatedFields() {
        Field[] fields = mRetriever.getFields(TestModels.PublicAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, FieldMatcher.haveNames("a", "b"));
    }

    @Test
    public void testRetrieveMixedAnnotatedFields() {
        Field[] fields = mRetriever.getFields(TestModels.MixedPublicFields.class);
        assertNotNull(fields);
        assertThat(fields, FieldMatcher.haveNames("b"));
    }

    @Test
    public void testRetrieveAnnotatedFieldsWithAllModifiers() {
        Field[] fields = mRetriever.getFields(TestModels.AllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, FieldMatcher.haveNames("a", "b", "c", "d"));
    }

    @Test
    public void testRetrieveAnnotatedFieldsInheritance() {
        Field[] fields = mRetriever.getFields(TestModels.SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, FieldMatcher.haveNames("a", "b", "c", "d", "e", "f", "g", "h"));
    }

    @Test
    public void testRetrieveCachedAnnotatedFieldsInheritance() {
        Field[] fields = mRetriever.getFields(TestModels.SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, FieldMatcher.haveNames("a", "b", "c", "d", "e", "f", "g", "h"));
        /* When the method is called for the second time, the fields will be in cache. */
        fields = mRetriever.getFields(TestModels.SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, FieldMatcher.haveNames("a", "b", "c", "d", "e", "f", "g", "h"));
    }
}