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

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

/**
 * Manager used by {@link Application} to save/restore state automatically in {@link Activity}.
 * The save is called when the {@link Activity} is created (before the {@code super.onCreate()} method).
 * The restore is called when the {@link Activity} needs to save the state (for a configuration change for example).
 * <br>
 * This class will be registered automatically in the {@link Application}.
 */
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class AutomaticSaveStateManager implements Application.ActivityLifecycleCallbacks {
    private Listener mListener;

    /**
     * Creates a new instance of {@link AutomaticSaveStateManager} with a listener used
     * to notify when the save/restore of the state must happen.
     *
     * @param listener listener used to receive callbacks to save/restore state
     */
    public AutomaticSaveStateManager(@NonNull Listener listener) {
        mListener = listener;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mListener.onRestoreState(activity, savedInstanceState);
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
        mListener.onSaveState(activity, outState);
    }

    @Override
    public void onActivityDestroyed(Activity activity) { /* empty */ }

    /**
     * Listener used to notify when the save/restore of the state must happen.
     */
    public interface Listener {

        /**
         * Called when the state of the {@link Activity} must be saved.
         *
         * @param activity {@link Activity} instance that must save the state
         * @param outState state that needs to be saved
         */
        void onSaveState(@NonNull Activity activity, @NonNull Bundle outState);

        /**
         * Called when the state of the {@link Activity} must be restored.
         *
         * @param activity   {@link Activity} instance that must restore the state
         * @param savedState state that needs to be restored
         */
        void onRestoreState(@NonNull Activity activity, @Nullable Bundle savedState);
    }
}