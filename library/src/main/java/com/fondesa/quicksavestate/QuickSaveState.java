package com.fondesa.quicksavestate;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;

import java.lang.reflect.Field;

/**
 * Created by antoniolig on 17/02/17.
 */
public final class QuickSaveState {
    private static final String TAG = QuickSaveState.class.getSimpleName();

    private CoderRetriever mCoderRetriever;
    private FieldsRetriever mFieldsRetriever;

    private static QuickSaveState instance;

    public static void init(@NonNull Application application) {
        if (instance != null) {
            Log.w(TAG, "The instance is initialized. You have called init() multiple times.");
        }
        instance = new QuickSaveState(application);
    }

    public static QuickSaveState instance() {
        if (instance == null) {
            throw getNullInstanceException();
        }
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    public void saveState(@NonNull Object stateHolder, @NonNull Bundle state) {
        Field[] cachedFields = mFieldsRetriever.getFields(stateHolder.getClass());

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < cachedFields.length; i++) {
            Field field = cachedFields[i];
            SaveState saveState = field.getAnnotation(SaveState.class);
            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }

            final String fieldName = field.getName();
            final Object fieldValue;
            try {
                fieldValue = field.get(stateHolder);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot access to field " + fieldName +
                        " of class " + stateHolder.getClass().getName());
            }

            if (fieldValue != null) {
                final StateCoder stateCoder = mCoderRetriever.getCoder(saveState, field.getType());
                //noinspection unchecked
                stateCoder.serialize(state, fieldName, fieldValue);
            }

            if (!accessible) {
                field.setAccessible(false);
            }
        }
    }

    public void restoreState(@NonNull Object stateHolder, @Nullable Bundle state) {
        if (state == null)
            return;

        Field[] cachedFields = mFieldsRetriever.getFields(stateHolder.getClass());
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < cachedFields.length; i++) {
            Field field = cachedFields[i];
            SaveState saveState = field.getAnnotation(SaveState.class);
            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }

            final StateCoder stateCoder = mCoderRetriever.getCoder(saveState, field.getType());

            final String fieldName = field.getName();
            final Object fieldValue = stateCoder.deserialize(state, fieldName);
            if (fieldValue != null) {
                try {
                    field.set(stateHolder, fieldValue);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot access to field " + fieldName +
                            " of class " + stateHolder.getClass().getName());
                }
            }

            if (!accessible) {
                field.setAccessible(false);
            }
        }
    }

    private static NullPointerException getNullInstanceException() {
        return new NullPointerException("Instance not initialized. You have to call " +
                QuickSaveState.class.getSimpleName() + "init() in your application.");
    }

    private QuickSaveState(@NonNull Application application) {
        mCoderRetriever = new DefaultCoderRetriever();
        mFieldsRetriever = new DefaultFieldsRetriever(SaveState.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    restoreState(activity, savedInstanceState);
                }

                @Override
                public void onActivityStarted(Activity activity) { /* empty */ }

                @Override
                public void onActivityResumed(Activity activity) { /* empty */ }

                @Override
                public void onActivityPaused(Activity activity) { /* empty */ }

                @Override
                public void onActivityStopped(Activity activity) { /* empty */ }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    saveState(activity, outState);
                }

                @Override
                public void onActivityDestroyed(Activity activity) { /* empty */ }
            });
        }
    }
}