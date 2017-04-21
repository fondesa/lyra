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

package com.fondesa.lyra.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.lyra.coder.StateCoder;

/**
 * Implementation of {@link StateCoder} to manage {@code int} fields.
 */
public class IntCoder extends BaseCoder<Integer> {

    /**
     * Write a field's value into the saved state {@link Bundle}.
     *
     * @param state      {@link Bundle} used to save the state
     * @param key        key retrieved from {@code fieldDeclaringClass#fieldName}
     * @param fieldValue value of field
     */
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String key, @NonNull Integer fieldValue) {
        state.putInt(key, fieldValue);
    }
}