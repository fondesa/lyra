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

package com.fondesa.lyra

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi

/**
 * Manager used by [Application] to save/restore state automatically in [Activity].
 * The save is called when the [Activity] is created (before the `super.onCreate()` method).
 * The restore is called when the [Activity] needs to save the state (for a configuration change for example).
 * This class will be registered automatically in the [Application].
 *
 * @param listener listener used to receive callbacks to save/restore state.
 */
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
class AutomaticSaveStateManager(private val listener: Listener) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        listener.onRestoreState(activity, savedInstanceState)
    }

    override fun onActivityStarted(activity: Activity) {
        // Empty implementation.
    }

    override fun onActivityResumed(activity: Activity) {
        // Empty implementation.
    }

    override fun onActivityPaused(activity: Activity) {
        // Empty implementation.
    }

    override fun onActivityStopped(activity: Activity) {
        // Empty implementation.
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        listener.onSaveState(activity, outState)
    }

    override fun onActivityDestroyed(activity: Activity) {
        // Empty implementation.
    }

    /**
     * Listener used to notify when the save/restore of the state must happen.
     */
    interface Listener {

        /**
         * Called when the state of the [Activity] must be saved.
         *
         * @param activity [Activity] instance that must save the state
         * @param outState state that needs to be saved
         */
        fun onSaveState(activity: Activity, outState: Bundle)

        /**
         * Called when the state of the [Activity] must be restored.
         *
         * @param activity   [Activity] instance that must restore the state
         * @param savedState state that needs to be restored
         */
        fun onRestoreState(activity: Activity, savedState: Bundle?)
    }
}