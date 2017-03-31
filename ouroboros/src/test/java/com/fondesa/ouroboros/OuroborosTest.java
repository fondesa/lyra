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

package com.fondesa.ouroboros;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.ouroboros.annotation.SaveState;
import com.fondesa.ouroboros.coder.DefaultCoderRetriever;
import com.fondesa.ouroboros.coder.StateCoder;
import com.fondesa.ouroboros.common.FieldAccessibleRunner;
import com.fondesa.ouroboros.common.TestModels;
import com.fondesa.ouroboros.field.DefaultFieldsRetriever;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.ApplicationTestUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Unit test class for {@link Ouroboros}.
 */
@RunWith(RobolectricTestRunner.class)
public class OuroborosTest {
    private EnhancedRandom mRandomizer;

    @Before
    public void setUp() {
        // init the instance before each test
        Application app = ApplicationTestUtil.newApplication(Application.class);
        Ouroboros.with(app).build();
        mRandomizer = EnhancedRandomBuilder.aNewEnhancedRandom();
    }

    @After
    public void tearDown() {
        // destroy the instance after each test
        Ouroboros.destroy();
    }

    @Test
    public void testIsInitialized() {
        assertTrue(Ouroboros.isInitialized());
    }

    @Test
    public void testSaveState() throws Exception {
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        final Bundle stateBundle = new Bundle();
        final TestModels.SubClassAllModifiersAnnotatedFields saveStateObject =
                new TestModels.SubClassAllModifiersAnnotatedFields();

        Field[] fields = retriever.getFields(saveStateObject.getClass());
        for (Field field : fields) {
            new FieldAccessibleRunner(field) {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    field.set(saveStateObject, mRandomizer.nextObject(field.getType()));
                }
            };
        }

        Ouroboros.instance().saveState(saveStateObject, stateBundle);

        List<Object> fieldValues = listOfValuesFromFields(saveStateObject, fields);
        List<Object> savedValues = new ArrayList<>(fieldValues.size());
        for (String key : stateBundle.keySet()) {
            savedValues.add(stateBundle.get(key));
        }

        assertThat(savedValues, containsInAnyOrder(fieldValues.toArray()));
    }

    @Test
    public void testRestoreState() throws Exception {
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        final DefaultCoderRetriever coderRetriever = new DefaultCoderRetriever();
        final Bundle stateBundle = new Bundle();
        final TestModels.SubClassAllModifiersAnnotatedFields saveStateObject =
                new TestModels.SubClassAllModifiersAnnotatedFields();

        Field[] fields = retriever.getFields(saveStateObject.getClass());
        List<Object> savedValues = new ArrayList<>(fields.length);
        for (Field field : fields) {
            SaveState saveState = field.getAnnotation(SaveState.class);
            Class<?> fieldType = field.getType();
            StateCoder stateCoder = coderRetriever.getCoder(saveState, fieldType);
            Object randomObj = mRandomizer.nextObject(fieldType);
            savedValues.add(randomObj);
            //noinspection unchecked
            stateCoder.serialize(stateBundle, field.getName(), randomObj);
        }

        Ouroboros.instance().restoreState(saveStateObject, stateBundle);

        List<Object> fieldValues = listOfValuesFromFields(saveStateObject, fields);
        assertThat(savedValues, containsInAnyOrder(fieldValues.toArray()));
    }

    @Test
    public void testSaveAndRestoreState() throws Exception {
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        final Bundle stateBundle = new Bundle();
        final TestModels.SubClassAllModifiersAnnotatedFields saveStateObject =
                new TestModels.SubClassAllModifiersAnnotatedFields();

        Field[] fields = retriever.getFields(saveStateObject.getClass());
        for (Field field : fields) {
            new FieldAccessibleRunner(field) {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    field.set(saveStateObject, mRandomizer.nextObject(field.getType()));
                }
            };
        }

        List<Object> fieldSavedValues = listOfValuesFromFields(saveStateObject, fields);

        Ouroboros.instance().saveState(saveStateObject, stateBundle);
        Ouroboros.instance().restoreState(saveStateObject, stateBundle);

        List<Object> fieldRestoredValues = listOfValuesFromFields(saveStateObject, fields);

        assertThat(fieldRestoredValues, containsInAnyOrder(fieldSavedValues.toArray()));
    }

    private static List<Object> listOfValuesFromFields(@NonNull final Object holder, @NonNull Field[] fields) throws Exception {
        final List<Object> fieldValues = new ArrayList<>(fields.length);
        for (Field field : fields) {
            new FieldAccessibleRunner(field) {
                @Override
                protected void runWithField(@NonNull Field field) throws Exception {
                    fieldValues.add(field.get(holder));
                }
            };
        }
        return fieldValues;
    }
}