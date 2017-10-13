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

package com.fondesa.lyra.field

import android.support.v4.util.ArrayMap
import com.fondesa.lyra.annotation.SaveState
import java.lang.reflect.Field


/**
 * Default implementation of [FieldsRetriever] that handles the cache for retrieved fields.
 * <br></br>
 * The fields are retrieved from the full list of fields of the class and each superclass.
 * In this implementation, all visibility modifiers in fields are accepted.
 * <br></br>
 * The class used as key for the cache, will be the class passed to the method [.getFields].
 * This means that referring to A, B and C as three classes with fields annotated with [SaveState],
 * if A extends B and C extends B, the classes inserted in cache are A and C.
 */
class DefaultFieldsRetriever : FieldsRetriever {
    private val classFieldMap: ArrayMap<Class<*>, Array<Field>> = ArrayMap()

    /**
     * Retrieve an array of [Field] from a class.
     *
     * @param cls java class containing the fields that will be saved
     * @return array of [Field] annotated with [SaveState]
     */
    override fun getFields(cls: Class<*>): Array<Field> {
        var cachedFields: Array<Field>? = classFieldMap[cls]
        // If the cache is valid, fields will be returned.
        if (cachedFields != null)
            return cachedFields

        var currentClass: Class<*>? = cls
        val futureCachedFields = mutableListOf<Field>()
        do {
            val declaredFields = currentClass!!.declaredFields
            for (i in 0 until currentClass.declaredFields.size) {
                val field = declaredFields[i]
                // If the field isn't annotated with @SaveState, the loop will continue to the next field.
                field.getAnnotation(SaveState::class.java) ?: continue

                futureCachedFields.add(field)
            }
            currentClass = currentClass.superclass
        } while (currentClass != null)

        // Create the array from the list.
        cachedFields = futureCachedFields.toTypedArray()
        // Put the fields in cache.
        classFieldMap.put(cls, cachedFields)
        return cachedFields
    }
}
