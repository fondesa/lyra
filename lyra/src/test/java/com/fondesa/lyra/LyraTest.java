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

package com.fondesa.lyra;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fondesa.lyra.annotation.SaveState;
import com.fondesa.lyra.common.TestModels;
import com.fondesa.lyra.sharedtest.FieldAccessibleRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.ApplicationTestUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Unit test class for {@link Lyra}.
 */
@RunWith(RobolectricTestRunner.class)
public class LyraTest {
    private EnhancedRandom mRandomizer;

    @Before
    public void setUp() {
        // init the instance before each test
        Application app = ApplicationTestUtil.newApplication(Application.class);
        Lyra.with(app).build();
        mRandomizer = EnhancedRandomBuilder.aNewEnhancedRandom();
    }

    @After
    public void tearDown() {
        // destroy the instance after each test
        Lyra.destroy();
    }

    @Test
    public void testInstanceNotNull() {
        assertNotNull(Lyra.instance());
    }

    @Test
    public void testDestroy() {
        Lyra.destroy();
        assertFalse(Lyra.isInitialized());
    }

    @Test
    public void testIsInitialized() {
        assertTrue(Lyra.isInitialized());
    }

    @Test
    public void testKeyFromFieldNotNull() {
        Field field = TestModels.MixedPublicFields.class.getDeclaredFields()[0];
        assertNotNull(Lyra.getKeyFromField(field));
    }

    @Test
    public void testSaveState() throws Exception {
        final TestModels.SubClassAllModifiersAnnotatedFields saveStateObject =
                new TestModels.SubClassAllModifiersAnnotatedFields();

        final Bundle stateBundle = new Bundle();
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        Field[] fields = retriever.getFields(saveStateObject.getClass());
        for (Field field : fields) {
            new FieldAccessibleRunner(field) {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    field.set(saveStateObject, mRandomizer.nextObject(field.getType()));
                }
            };
        }

        Lyra.instance().saveState(saveStateObject, stateBundle);

        List<Object> fieldValues = listOfValuesFromFields(saveStateObject, fields);
        List<Object> savedValues = new ArrayList<>(fieldValues.size());

        Bundle lyraBundle = stateBundle.getBundle(Lyra.SUB_BUNDLE_KEY);
        // Assert that the sub Bundle has been written.
        assertNotNull(lyraBundle);

        for (String key : lyraBundle.keySet()) {
            savedValues.add(lyraBundle.get(key));
        }

        assertThat(savedValues, containsInAnyOrder(fieldValues.toArray()));
    }

    @Test
    public void testRestoreState() throws Exception {
        final TestModels.SubClassAllModifiersAnnotatedFields saveStateObject =
                new TestModels.SubClassAllModifiersAnnotatedFields();

        final Bundle stateBundle = new Bundle();
        final Bundle lyraBundle = new Bundle();
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        final DefaultCoderRetriever coderRetriever = new DefaultCoderRetriever();
        Field[] fields = retriever.getFields(saveStateObject.getClass());
        List<Object> savedValues = new ArrayList<>(fields.length);
        for (Field field : fields) {
            SaveState saveState = field.getAnnotation(SaveState.class);
            Class<?> fieldType = field.getType();
            StateCoder stateCoder = coderRetriever.getCoder(saveState, fieldType);
            Object randomObj = mRandomizer.nextObject(fieldType);
            savedValues.add(randomObj);
            //noinspection unchecked
            stateCoder.serialize(lyraBundle, Lyra.getKeyFromField(field), randomObj);
        }

        // Insert the sub Bundle used by Lyra.
        stateBundle.putBundle(Lyra.SUB_BUNDLE_KEY, lyraBundle);
        // Restore the state from the sub Bundle.
        Lyra.instance().restoreState(saveStateObject, stateBundle);

        List<Object> fieldValues = listOfValuesFromFields(saveStateObject, fields);
        assertThat(savedValues, containsInAnyOrder(fieldValues.toArray()));
    }

    @Test
    public void testSaveAndRestoreState() throws Exception {
        final TestModels.SubClassAllModifiersAnnotatedFields saveStateObject =
                new TestModels.SubClassAllModifiersAnnotatedFields();

        final Bundle stateBundle = new Bundle();
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
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

        Lyra.instance().saveState(saveStateObject, stateBundle);
        Lyra.instance().restoreState(saveStateObject, stateBundle);

        List<Object> fieldRestoredValues = listOfValuesFromFields(saveStateObject, fields);

        assertThat(fieldRestoredValues, containsInAnyOrder(fieldSavedValues.toArray()));
    }

    @Test
    public void testSaveParcelableState() throws Exception {
        final Context context = RuntimeEnvironment.application.getApplicationContext();
        final TestModels.SaveStateView saveStateView = new TestModels.SaveStateView(context);

        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        Field[] fields = retriever.getFields(saveStateView.getClass());
        for (Field field : fields) {
            new FieldAccessibleRunner(field) {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    field.set(saveStateView, mRandomizer.nextObject(field.getType()));
                }
            };
        }

        Parcelable parcelable = TestModels.ImplementedParcelable.getDefault();
        Parcelable stateParcelable = Lyra.instance().saveState(saveStateView, parcelable);

        assertTrue(stateParcelable instanceof Bundle);

        Bundle stateBundle = (Bundle) stateParcelable;
        List<Object> fieldValues = listOfValuesFromFields(saveStateView, fields);
        List<Object> savedValues = new ArrayList<>(fieldValues.size());

        Bundle lyraBundle = stateBundle.getBundle(Lyra.SUB_BUNDLE_KEY);
        // Assert that the sub Bundle has been written.
        assertNotNull(lyraBundle);

        for (String key : lyraBundle.keySet()) {
            savedValues.add(lyraBundle.get(key));
        }

        assertThat(savedValues, containsInAnyOrder(fieldValues.toArray()));
    }

    @Test
    public void testRestoreParcelableState() throws Exception {
        final Context context = RuntimeEnvironment.application.getApplicationContext();
        final TestModels.SaveStateView saveStateView = new TestModels.SaveStateView(context);

        Parcelable parcelable = TestModels.ImplementedParcelable.getDefault();

        final Bundle stateBundle = new Bundle();
        stateBundle.putParcelable(Lyra.VIEW_SUPER_STATE_BUNDLE_KEY, parcelable);

        final Bundle lyraBundle = new Bundle();
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        final DefaultCoderRetriever coderRetriever = new DefaultCoderRetriever();
        Field[] fields = retriever.getFields(saveStateView.getClass());
        List<Object> savedValues = new ArrayList<>(fields.length);
        for (Field field : fields) {
            SaveState saveState = field.getAnnotation(SaveState.class);
            Class<?> fieldType = field.getType();
            StateCoder stateCoder = coderRetriever.getCoder(saveState, fieldType);
            Object randomObj = mRandomizer.nextObject(fieldType);
            savedValues.add(randomObj);
            //noinspection unchecked
            stateCoder.serialize(lyraBundle, Lyra.getKeyFromField(field), randomObj);
        }

        // Insert the sub Bundle used by Lyra.
        stateBundle.putBundle(Lyra.SUB_BUNDLE_KEY, lyraBundle);
        // Restore the state from the sub Bundle.
        Parcelable restoredParcelable = Lyra.instance().restoreState(saveStateView, (Parcelable) stateBundle);
        assertEquals(restoredParcelable, parcelable);

        List<Object> fieldValues = listOfValuesFromFields(saveStateView, fields);
        assertThat(savedValues, containsInAnyOrder(fieldValues.toArray()));
    }

    @Test
    public void testSaveAndRestoreParcelableState() throws Exception {
        final Context context = RuntimeEnvironment.application.getApplicationContext();
        final TestModels.SaveStateView saveStateView = new TestModels.SaveStateView(context);

        Parcelable parcelable = TestModels.ImplementedParcelable.getDefault();

        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        Field[] fields = retriever.getFields(saveStateView.getClass());
        for (Field field : fields) {
            new FieldAccessibleRunner(field) {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    field.set(saveStateView, mRandomizer.nextObject(field.getType()));
                }
            };
        }

        List<Object> fieldSavedValues = listOfValuesFromFields(saveStateView, fields);

        Parcelable savedState = Lyra.instance().saveState(saveStateView, parcelable);
        Parcelable restoredParcelable = Lyra.instance().restoreState(saveStateView, savedState);

        assertEquals(restoredParcelable, parcelable);

        List<Object> fieldRestoredValues = listOfValuesFromFields(saveStateView, fields);
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