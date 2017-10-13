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

import com.fondesa.lyra.Lyra
import com.fondesa.lyra.annotation.SaveState
import java.lang.reflect.Field

/**
 * Interface that manages the retrieving of fields in a java class.
 * The default implementation is: [DefaultFieldsRetriever].
 * You can implement your own [FieldsRetriever] and set it in the singleton instance
 * of [Lyra] with [Lyra.Builder.fieldsRetriever]
 * (for example to optimize performance or handle the cache differently).
 */
interface FieldsRetriever {

    /**
     * Retrieve an array of [Field] from a class.
     *
     * @param cls java class containing the fields that will be saved
     * @return array of [Field] annotated with [SaveState]
     */
    fun getFields(cls: Class<*>): Array<Field>
}