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

package com.fondesa.lyra.coder

import android.os.Bundle

import com.fondesa.lyra.annotation.SaveState

/**
 * Interface used to specify a custom serializer/deserializer for a save state [Bundle].
 * The custom coder can be passed to the annotation with [SaveState.value].
 *
 * @param <FieldType> type of the value that will be serialized/deserialized
 */
interface StateCoder<FieldType : Any> {

    /**
     * Write a field's value into the saved state [Bundle].
     *
     * @param state      [Bundle] used to save the state
     * @param key        key retrieved from `fieldDeclaringClass#fieldName`
     * @param fieldValue value of field
     */
    fun serialize(state: Bundle, key: String, fieldValue: FieldType)

    /**
     * Read a field's value from the saved state [Bundle].
     *
     * @param state [Bundle] used to save the state
     * @param key   key retrieved from `fieldDeclaringClass#fieldName`
     * @return value of the field
     */
    fun deserialize(state: Bundle, key: String): FieldType
}