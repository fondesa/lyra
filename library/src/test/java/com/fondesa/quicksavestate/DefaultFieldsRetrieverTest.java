package com.fondesa.quicksavestate;

import com.fondesa.quicksavestate.common.TestModels;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Field;

import static com.fondesa.quicksavestate.FieldMatcher.haveNames;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by antoniolig on 05/03/17.
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
        assertThat(fields, haveNames("a", "b"));
    }

    @Test
    public void testRetrieveMixedAnnotatedFields() {
        Field[] fields = mRetriever.getFields(TestModels.MixedPublicFields.class);
        assertNotNull(fields);
        assertThat(fields, haveNames("b"));
    }

    @Test
    public void testRetrieveAnnotatedFieldsWithAllModifiers() {
        Field[] fields = mRetriever.getFields(TestModels.AllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, haveNames("a", "b", "c", "d"));
    }

    @Test
    public void testRetrieveAnnotatedFieldsInheritance() {
        Field[] fields = mRetriever.getFields(TestModels.SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, haveNames("a", "b", "c", "d", "e", "f", "g", "h"));
    }

    @Test
    public void testRetrieveCachedAnnotatedFieldsInheritance() {
        Field[] fields = mRetriever.getFields(TestModels.SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, haveNames("a", "b", "c", "d", "e", "f", "g", "h"));
        /* When the method is called for the second time, the fields will be in cache. */
        fields = mRetriever.getFields(TestModels.SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, haveNames("a", "b", "c", "d", "e", "f", "g", "h"));
    }
}