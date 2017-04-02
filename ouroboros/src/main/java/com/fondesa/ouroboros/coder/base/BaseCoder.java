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

package com.fondesa.ouroboros.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.ouroboros.coder.StateCoder;

/**
 * Basic implementation of {@link StateCoder} that will deserialize the field from
 * the {@link Bundle} and automatically cast it.
 *
 * @param <FieldType> type of the value that will be serialized/deserialized
 */
abstract class BaseCoder<FieldType> implements StateCoder<FieldType> {

    /**
     * Read a field's value from the saved state {@link Bundle}.
     *
     * @param state     {@link Bundle} used to save the state
     * @param fieldName name of the field
     * @return value of the field
     */
    @Override
    public FieldType deserialize(@NonNull Bundle state, @NonNull String fieldName) {
        //noinspection unchecked
        return (FieldType) state.get(fieldName);
    }
}