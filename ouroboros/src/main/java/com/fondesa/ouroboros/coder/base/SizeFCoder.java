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

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.SizeF;

import com.fondesa.ouroboros.coder.StateCoder;

/**
 * Implementation of {@link StateCoder} to manage {@code SizeF} fields.
 * <br>
 * This coder is available only on api above or equal to 21.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SizeFCoder extends BaseCoder<SizeF> {

    /**
     * Write a field's value into the saved state {@link Bundle}.
     *
     * @param state      {@link Bundle} used to save the state
     * @param fieldName  name of the field
     * @param fieldValue value of field
     */
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull SizeF fieldValue) {
        state.putSizeF(fieldName, fieldValue);
    }
}