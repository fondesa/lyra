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

package com.fondesa.lyra.coder;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.lyra.annotation.SaveState;

/**
 * Interface used to specify a custom serializer/deserializer for a save state {@link Bundle}.
 * The custom coder can be passed to the annotation with {@link SaveState#value()}.
 *
 * @param <FieldType> type of the value that will be serialized/deserialized
 */
public interface StateCoder<FieldType> {

    /**
     * Write a field's value into the saved state {@link Bundle}.
     *
     * @param state      {@link Bundle} used to save the state
     * @param fieldName  name of the field
     * @param fieldValue value of field
     */
    void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull FieldType fieldValue);

    /**
     * Read a field's value from the saved state {@link Bundle}.
     *
     * @param state     {@link Bundle} used to save the state
     * @param fieldName name of the field
     * @return value of the field
     */
    FieldType deserialize(@NonNull Bundle state, @NonNull String fieldName);
}