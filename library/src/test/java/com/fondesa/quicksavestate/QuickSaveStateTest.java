package com.fondesa.quicksavestate;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.common.TestSaveStateActivity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;
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
    public void testInit() {
        assertTrue(QuickSaveState.isInitialized());
    }

    @Test
    public void testActivitySaveState() throws Exception {
        final Class<? extends Activity> activityClass = TestSaveStateActivity.class;
        final Bundle outState = new Bundle();
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();

        ActivityController<? extends Activity> controller = Robolectric.buildActivity(activityClass).create();
        final Activity activity = controller.get();

        Field[] fields = retriever.getFields(activityClass);
        for (Field field : fields) {
            runWithAccessibleField(field, new FieldAccessibleRunnable() {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    field.set(activity, mRandomizer.nextObject(field.getType()));
                }
            });
        }

        controller.saveInstanceState(outState);

        Object[] fieldValues = getValuesFromFields(activity, fields);
        boolean valueContained = false;
        for (Object value : fieldValues) {
            valueContained = false;
            for (String key : outState.keySet()) {
                Object bundleValue = outState.get(key);
                if (value.equals(bundleValue)) {
                    valueContained = true;
                    break;
                }
            }
            if (!valueContained)
                break;
        }
        assertTrue(valueContained);
    }

    @Test
    public void testActivityRestoreState() throws Exception {
        final Class<? extends Activity> activityClass = TestSaveStateActivity.class;
        final Bundle savedState = new Bundle();
        final DefaultFieldsRetriever retriever = new DefaultFieldsRetriever();
        final DefaultCoderRetriever coderRetriever = new DefaultCoderRetriever();

        Field[] fields = retriever.getFields(activityClass);
        List<Object> savedValues = new ArrayList<>(fields.length);
        for (Field field : fields) {
            SaveState saveState = field.getAnnotation(SaveState.class);
            Class<?> fieldType = field.getType();
            StateCoder stateCoder = coderRetriever.getCoder(saveState, fieldType);
            Object randomObj = mRandomizer.nextObject(fieldType);
            savedValues.add(randomObj);
            //noinspection unchecked
            stateCoder.serialize(savedState, field.getName(), randomObj);
        }

        Activity activity = Robolectric.buildActivity(activityClass)
                .create(savedState)
                .get();


        Object[] fieldValues = getValuesFromFields(activity, fields);
        assertThat(savedValues, containsInAnyOrder(fieldValues));
    }

    private static Object[] getValuesFromFields(@NonNull final Object holder, @NonNull Field[] fields) throws Exception {
        final List<Object> fieldValues = new ArrayList<>(fields.length);
        for (Field field : fields) {
            runWithAccessibleField(field, new FieldAccessibleRunnable() {
                @Override
                public void runWithField(@NonNull Field field) throws Exception {
                    fieldValues.add(field.get(holder));
                }
            });
        }
        return fieldValues.toArray();
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
