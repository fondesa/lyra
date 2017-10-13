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

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import com.fondesa.lyra.Lyra.Builder
import com.fondesa.lyra.annotation.SaveState
import com.fondesa.lyra.coder.CoderRetriever
import com.fondesa.lyra.coder.DefaultCoderRetriever
import com.fondesa.lyra.coder.StateCoder
import com.fondesa.lyra.exception.CoderNotFoundException
import com.fondesa.lyra.field.DefaultFieldsRetriever
import com.fondesa.lyra.field.FieldsRetriever
import java.lang.reflect.Field

/**
 * Manager to save/restore the fields' state of an object's instance into/from a [Bundle].
 * You have to annotate the global variables that you want to save/restore with the runtime annotation [SaveState].
 * All basic types will be resolved through basic coders contained in the package [com.fondesa.lyra.coder.base].
 * If you want to use a custom coder, you have to implement [StateCoder] interface and manage manually the
 * write to a [Bundle] and the read from a [Bundle]. After, you have to annotate your field with the
 * [SaveState] annotation passing the new coder as the value inside the annotation.
 * The save/restore process supports class' inheritance. You can annotate a field of a class and all its subclasses (if
 * they use [Lyra]) will save and restore the state of that field.
 * This class uses the singleton pattern so you must initialize it once using the provided [Builder].
 * After, you can access to instance methods with [Lyra.instance].
 */
class Lyra(application: Application,
           private val coderRetriever: CoderRetriever,
           private val fieldsRetriever: FieldsRetriever,
           autoSaveActivities: Boolean) {

    companion object {
        const val SUB_BUNDLE_KEY = "lyra:"
        const val VIEW_SUPER_STATE_BUNDLE_KEY = "view:superState"
        private val TAG = Lyra::class.java.simpleName

        private var backingInstance: Lyra? = null

        /**
         * Get the singleton instance of [Lyra] if it's initialized.
         *
         * @return not null instance of [Lyra]
         */
        @JvmStatic
        val instance: Lyra
            get() {
                if (!isInitialized()) {
                    throw NullPointerException("Instance not initialized. You have to build it in your application.")
                }
                return backingInstance!!
            }

        @Deprecated("The method instance() is now deprecated.",
                ReplaceWith("instance", "com.fondesa.lyra.Lyra.Companion.instance"))
        @JvmStatic
        fun instance(): Lyra = instance

        /**
         * Check if the singleton instance of [Lyra] is initialized.
         *
         * @return true if the instance is not null
         */
        fun isInitialized(): Boolean = backingInstance != null

        /**
         * Creates a [Builder] instance to specify [Lyra]'s configurations.
         *
         * @param application [Application]'s instance used to create the [Builder]
         * @return a new [Builder] to generate the singleton instance of [Lyra]
         */
        fun with(application: Application): Builder = Builder().with(application)

        /**
         * Destroy the singleton instance if it's initialized.
         * This will avoid memory leak due to usage of [Application] inside a singleton class.
         * If there's an [AutomaticSaveStateManager] registered for lifecycle callbacks,
         * it will be unregistered.
         */
        @SuppressLint("NewApi")
        fun destroy() {
            if (isInitialized()) {
                instance.automaticSaveStateManager?.let {
                    instance.application?.unregisterActivityLifecycleCallbacks(it)
                }
                // Avoid memory leak bringing Application instance to null.
                instance.application = null
                backingInstance = null
            }
        }

        /**
         * Get the key used to save/restore a class' field.
         * The format will be: `fieldDeclaringClass#fieldName`.
         *
         * @param field [Field] used to get the key
         * @return key used to save/restore a [Field] instance
         */
        fun getKeyFromField(field: Field): String = field.declaringClass.name + '#' + field.name
    }

    private var application: Application? = null
    private var automaticSaveStateManager: AutomaticSaveStateManager? = null

    init {
        this.application = application

        if (autoSaveActivities) {
            // The registration for lifecycle events is available above api 14.
            val neededApi = Build.VERSION_CODES.ICE_CREAM_SANDWICH
            if (Build.VERSION.SDK_INT >= neededApi) {
                // Set the listener to handle the save and the restore of the state.
                val listener = object : AutomaticSaveStateManager.Listener {
                    override fun onSaveState(activity: Activity, outState: Bundle) {
                        Lyra.instance().saveState(activity, outState)
                    }

                    override fun onRestoreState(activity: Activity, savedState: Bundle?) {
                        Lyra.instance().restoreState(activity, savedState)
                    }
                }
                automaticSaveStateManager = AutomaticSaveStateManager(listener)
                // Register the AutomaticSaveStateManager for activities' lifecycle events.
                application.registerActivityLifecycleCallbacks(automaticSaveStateManager)
            } else {
                Log.e(TAG, "State can't be automatically saved below api " + neededApi)
            }
        }
    }

    /**
     * Save the annotated fields' state of a class into a [Bundle].
     * The fields of the class will be retrieved through a [FieldsRetriever].
     * The coder to serialize the fields into a [Bundle] will be retrieved through a [CoderRetriever].
     *
     * @param stateHolder instance of the class with annotated fields
     * @param state       [Bundle] in which you want to save the annotated fields
     */
    fun saveState(stateHolder: Any, state: Bundle) {
        // Get the instance through the method to have the correct exception handling if instance is null.
        val lyra = instance
        val fields = lyra.fieldsRetriever.getFields(stateHolder.javaClass)

        val lyraBundle = Bundle()


        for (i in fields.indices) {
            val field = fields[i]
            val saveState = getSaveStateAnnotation(field)
            // If the field isn't accessible, it will be modified in accessible and will return inaccessible after.
            val accessible = field.isAccessible
            if (!accessible) {
                field.isAccessible = true
            }

            val fieldValue: Any?
            try {
                // Get the field value.
                fieldValue = field.get(stateHolder)
            } catch (e: IllegalAccessException) {
                throw runtimeIllegalAccessException(field)
            }

            if (fieldValue != null) {
                val stateCoder: StateCoder<Any>
                try {
                    stateCoder = lyra.coderRetriever.getCoder(saveState, field.type) as StateCoder<Any>
                } catch (e: CoderNotFoundException) {
                    throw RuntimeException(e)
                }

                stateCoder.serialize(lyraBundle, getKeyFromField(field), fieldValue)
            }

            if (!accessible) {
                // Modify the field to make it inaccessible again.
                field.isAccessible = false
            }
        }

        // Put the saved fields in the sub Bundle of Lyra.
        state.putBundle(SUB_BUNDLE_KEY, lyraBundle)
    }

    /**
     * Restore the annotated fields' state of a class from a [Bundle].
     * The fields of the class will be retrieved through a [FieldsRetriever].
     * The coder to deserialize the fields from a [Bundle] will be retrieved through a [CoderRetriever].
     *
     * @param stateHolder instance of the class with annotated fields
     * @param state       [Bundle] from which you want to restore the value of the annotated fields
     */
    fun restoreState(stateHolder: Any, state: Bundle?) {
        // Restore the saved fields from the sub Bundle of Lyra.
        val lyraBundle = state?.getBundle(SUB_BUNDLE_KEY) ?: return

        // Get the instance through the method to have the correct exception handling if instance is null.
        val lyra = instance
        val fields = lyra.fieldsRetriever.getFields(stateHolder.javaClass)

        for (i in fields.indices) {
            val field = fields[i]
            val saveState = getSaveStateAnnotation(field)
            // If the field isn't accessible, it will be modified in accessible and will return inaccessible after.
            val accessible = field.isAccessible
            if (!accessible) {
                field.isAccessible = true
            }

            val stateCoder: StateCoder<*>
            try {
                stateCoder = lyra.coderRetriever.getCoder(saveState, field.type)
            } catch (e: CoderNotFoundException) {
                throw RuntimeException(e)
            }

            val fieldValue = stateCoder.deserialize(lyraBundle, getKeyFromField(field))
            if (fieldValue != null) {
                try {
                    // Set the field value.
                    field.set(stateHolder, fieldValue)
                } catch (e: IllegalAccessException) {
                    throw runtimeIllegalAccessException(field)
                }

            }

            if (!accessible) {
                // Modify the field to make it inaccessible again.
                field.isAccessible = false
            }
        }
    }

    /**
     * Save the annotated fields' state of a [View] class into a [Bundle].
     * The [Bundle] will contain the parcelable state of the [View]
     * with the key [VIEW_SUPER_STATE_BUNDLE_KEY].
     * The fields of the class will be retrieved through a [FieldsRetriever].
     * The coder to serialize the fields into a [Bundle] will be retrieved through a [CoderRetriever].
     *
     * @param stateHolder instance of the class with annotated fields
     * @param state       [Bundle] in which you want to save the annotated fields
     * @return [Bundle] containing view state and Lyra sub-bundle
     */
    fun saveState(stateHolder: View, state: Parcelable?): Parcelable {
        val wrapper = Bundle()
        // Put the original super state.
        wrapper.putParcelable(VIEW_SUPER_STATE_BUNDLE_KEY, state)
        // Save the state into the Lyra Bundle.
        saveState(stateHolder as Any, wrapper)
        return wrapper
    }

    /**
     * Restore the annotated fields' state of a [View] class from a [Parcelable].
     * If the state is a [Bundle], the [View]'s state and the fields' values will be restored.
     * The fields of the class will be retrieved through a [FieldsRetriever].
     * The coder to deserialize the fields from a [Bundle] will be retrieved through a [CoderRetriever].
     *
     * @param stateHolder instance of the class with annotated fields
     * @param state       [Bundle] from which you want to restore the value of the annotated fields
     * @return [Parcelable] containing [View]'s saved state
     */
    fun restoreState(stateHolder: View, state: Parcelable?): Parcelable? {
        var state = state
        if (state is Bundle) {
            val wrapper = state as Bundle?
            // Get the original super state.
            state = wrapper!!.getParcelable(VIEW_SUPER_STATE_BUNDLE_KEY)
            // Restore the state saved in the Lyra Bundle.
            restoreState(stateHolder as Any, wrapper)
        }
        return state
    }

    private fun getSaveStateAnnotation(field: Field): SaveState {
        return field.getAnnotation(SaveState::class.java) ?: throw IllegalArgumentException("You must provide a list of fields with the "
                + SaveState::class.java.name + " annotation")
    }

    private fun runtimeIllegalAccessException(field: Field): RuntimeException {
        return RuntimeException("Cannot access to field " + field.name +
                " of class " + field.declaringClass.name)
    }

    /**
     * Builder class used to create the singleton instance of [Lyra].
     * To create a new instance of this [Builder] use [Lyra.with].
     */
    class Builder internal constructor() {
        private lateinit var application: Application
        private var coderRetriever: CoderRetriever? = null
        private var fieldsRetriever: FieldsRetriever? = null
        private var autoSaveActivities: Boolean = false

        /**
         * Set the [Application] that must be used as [Context] for this [Builder].
         *
         * @param application instance of [Application] in which the [Builder] is created
         * @return instance of the previously created [Builder]
         */
        internal fun with(application: Application) = apply { this.application = application }

        /**
         * Set the [CoderRetriever] that must be used to retrieve the coder related to a java class.
         * DEFAULT: [DefaultCoderRetriever]
         *
         * @param coderRetriever instance of [CoderRetriever] used to retrieve a [StateCoder]
         * @return instance of the previously created [Builder]
         */
        fun coderRetriever(coderRetriever: CoderRetriever) = apply { this.coderRetriever = coderRetriever }

        /**
         * Set the [FieldsRetriever] that must be used to retrieve the fields declared in a java class.
         * DEFAULT: [DefaultFieldsRetriever]
         *
         * @param fieldsRetriever instance of [FieldsRetriever] used to retrieve the list of fields
         * @return instance of the previously created [Builder]
         */
        fun fieldsRetriever(fieldsRetriever: FieldsRetriever) = apply { this.fieldsRetriever = fieldsRetriever }

        /**
         * Allows the automatic save/restore of the state in every [Activity].
         * It can be done through [Application.registerActivityLifecycleCallbacks].
         * An instance of [AutomaticSaveStateManager] will be attached to the [Lyra] to manage
         * the automatic save/restore of the state.
         * DEFAULT: false (the save/restore of the state must be called manually)
         *
         * @return instance of the previously created [Builder]
         */
        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        fun autoSaveActivities() = apply { autoSaveActivities = true }

        /**
         * Creates an instance of [Lyra] with the previously specified [Builder]'s
         * configurations or defaults if those are not defined.
         */
        fun build() {
            if (coderRetriever == null) {
                coderRetriever = DefaultCoderRetriever()
            }

            if (fieldsRetriever == null) {
                fieldsRetriever = DefaultFieldsRetriever()
            }

            if (backingInstance != null) {
                Log.w(TAG, "The instance is initialized. You are building it multiple times.")
            }

            // Create the singleton instance.
            backingInstance = Lyra(application, coderRetriever!!, fieldsRetriever!!, autoSaveActivities)
        }
    }
}