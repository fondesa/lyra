package com.fondesa.quicksavestate;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.common.TestModels;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ApplicationTestUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Created by antoniolig on 01/03/17.
 */
@RunWith(RobolectricTestRunner.class)
public class QuickSaveStateTest {
    private static EnhancedRandom mRandomizer;

    @BeforeClass
    public static void initInstance() {
        // init the instance before run all tests
        Application app = ApplicationTestUtil.newApplication(Application.class);
        QuickSaveState.with(app).build();
        mRandomizer = EnhancedRandomBuilder.aNewEnhancedRandom();
    }

    @AfterClass
    public static void destroyInstance() {
        // destroy the instance after all tests
        QuickSaveState.destroy();
        mRandomizer = null;
    }

    @Test
    public void testIsInitialized() {
        assertTrue(QuickSaveState.isInitialized());
    }

    @Test
    public void testActivitySaveState() throws Exception {
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        final Bundle stateBundle = new Bundle();
        final TestModels.SubClassAllModifiersAnnotatedFields saveStateObject =
                new TestModels.SubClassAllModifiersAnnotatedFields();

        Field[] fields = retriever.getFields(saveStateObject.getClass());
        for (Field field : fields) {
            runWithAccessibleField(field, new FieldAccessibleRunnable() {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    field.set(saveStateObject, mRandomizer.nextObject(field.getType()));
                }
            });
        }

        QuickSaveState.saveState(saveStateObject, stateBundle);

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

        QuickSaveState.restoreState(saveStateObject, stateBundle);

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
            runWithAccessibleField(field, new FieldAccessibleRunnable() {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    field.set(saveStateObject, mRandomizer.nextObject(field.getType()));
                }
            });
        }

        List<Object> fieldSavedValues = listOfValuesFromFields(saveStateObject, fields);

        QuickSaveState.saveState(saveStateObject, stateBundle);
        QuickSaveState.restoreState(saveStateObject, stateBundle);

        List<Object> fieldRestoredValues = listOfValuesFromFields(saveStateObject, fields);

        assertThat(fieldRestoredValues, containsInAnyOrder(fieldSavedValues.toArray()));
    }

    private static List<Object> listOfValuesFromFields(@NonNull final Object holder, @NonNull Field[] fields) throws Exception {
        final List<Object> fieldValues = new ArrayList<>(fields.length);
        for (Field field : fields) {
            runWithAccessibleField(field, new FieldAccessibleRunnable() {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    fieldValues.add(field.get(holder));
                }
            });
        }
        return fieldValues;
    }

    private static void runWithAccessibleField(@NonNull Field field, @NonNull FieldAccessibleRunnable runnable) throws Exception {
        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        runnable.runWithField(field);
        if (!accessible) {
            field.setAccessible(false);
        }
    }

    private interface FieldAccessibleRunnable {
        void runWithField(@NonNull Field field) throws Exception;
    }
}