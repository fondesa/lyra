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

package com.fondesa.quicksavestate;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;

import java.lang.reflect.Field;

/**
 * Manager to save/restore the state of an {@link Object}'s instance into/from a {@link Bundle}.
 * This class uses the singleton pattern so you must initialize it once using the provided {@link Builder}.
 * After, you can access to instance methods with {@link QuickSaveState#instance()}.
 */
public class QuickSaveState {
    private static final String TAG = QuickSaveState.class.getSimpleName();

    private Application mApplication;
    private CoderRetriever mCoderRetriever;
    private FieldsRetriever mFieldsRetriever;
    private AutomaticSaveStateManager mAutomaticSaveStateManager;

    @SuppressLint("StaticFieldLeak")
    private static QuickSaveState instance;

    /**
     *
     * @param application
     * @return
     */
    public static Builder with(@NonNull Application application) {
        return new QuickSaveState.Builder().with(application);
    }

    @SuppressLint("NewApi")
    public static void destroy() {
        if (isInitialized()) {
            if (instance.mAutomaticSaveStateManager != null) {
                instance.mApplication.unregisterActivityLifecycleCallbacks(instance.mAutomaticSaveStateManager);
            }
            instance.mApplication = null;
            instance = null;
        }
    }

    public static boolean isInitialized() {
        return instance != null;
    }

    public void saveState(@NonNull Object stateHolder, @NonNull Bundle state) {
        Field[] cachedFields = instance().mFieldsRetriever.getFields(stateHolder.getClass());

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
                final StateCoder stateCoder = instance().mCoderRetriever.getCoder(saveState, field.getType());
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

        Field[] cachedFields = instance().mFieldsRetriever.getFields(stateHolder.getClass());
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < cachedFields.length; i++) {
            Field field = cachedFields[i];
            SaveState saveState = field.getAnnotation(SaveState.class);
            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }

            final StateCoder stateCoder = instance().mCoderRetriever.getCoder(saveState, field.getType());

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

    public static QuickSaveState instance() {
        if (!isInitialized()) {
            throw new NullPointerException("Instance not initialized. You have to build it in your application.");
        }
        return instance;
    }

    @SuppressLint("NewApi")
    private QuickSaveState(@NonNull Application application,
                           @NonNull CoderRetriever coderRetriever,
                           @NonNull FieldsRetriever fieldsRetriever,
                           boolean autoSaveActivities) {

        mApplication = application;
        mCoderRetriever = coderRetriever;
        mFieldsRetriever = fieldsRetriever;

        if (autoSaveActivities) {
            final int neededApi = Build.VERSION_CODES.ICE_CREAM_SANDWICH;
            if (Build.VERSION.SDK_INT >= neededApi) {
                AutomaticSaveStateManager.Listener listener = new AutomaticSaveStateManager.Listener() {
                    @Override
                    public void onSaveState(@NonNull Object holder, @NonNull Bundle outState) {
                        QuickSaveState.instance().saveState(holder, outState);
                    }

                    @Override
                    public void onRestoreState(@NonNull Object holder, @Nullable Bundle savedState) {
                        QuickSaveState.instance().restoreState(holder, savedState);
                    }
                };
                mAutomaticSaveStateManager = new AutomaticSaveStateManager(listener);
                mApplication.registerActivityLifecycleCallbacks(mAutomaticSaveStateManager);
            } else {
                Log.e(TAG, "State can't be automatically saved below api " + neededApi);
            }
        }
    }

    public static final class Builder {
        private Application mApplication;
        private CoderRetriever mCoderRetriever;
        private FieldsRetriever mFieldsRetriever;
        private boolean mAutoSaveActivities;

        Builder with(@NonNull Application application) {
            mApplication = application;
            return this;
        }

        public Builder coderRetriever(@Nullable CoderRetriever coderRetriever) {
            mCoderRetriever = coderRetriever;
            return this;
        }

        public Builder fieldsRetriever(@Nullable FieldsRetriever fieldsRetriever) {
            mFieldsRetriever = fieldsRetriever;
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public Builder autoSaveActivities() {
            mAutoSaveActivities = true;
            return this;
        }

        public void build() {
            if (mCoderRetriever == null) {
                mCoderRetriever = new DefaultCoderRetriever();
            }

            if (mFieldsRetriever == null) {
                mFieldsRetriever = new DefaultFieldsRetriever();
            }

            if (instance != null) {
                Log.w(TAG, "The instance is initialized. You are building it multiple times.");
            }

            instance = new QuickSaveState(mApplication,
                    mCoderRetriever,
                    mFieldsRetriever,
                    mAutoSaveActivities);
        }
    }
}