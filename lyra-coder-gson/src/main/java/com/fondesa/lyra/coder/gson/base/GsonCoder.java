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

package com.fondesa.lyra.coder.gson.base;

import android.support.annotation.NonNull;

import com.fondesa.lyra.coder.StateCoder;
import com.google.gson.Gson;

/**
 * Basic class to implement a {@link Gson} coder that adds a {@link Gson} instance to
 * serialize/deserialize a field value.
 *
 * @param <ValueType> type of the object serialized/deserialized by {@link Gson}
 */
public abstract class GsonCoder<ValueType> implements StateCoder<ValueType> {
    private Gson mGson;

    /**
     * Creates a new instance of {@link GsonCoder} with a given {@link Gson} instance.
     *
     * @param gson instance of {@link Gson} used to serialize/deserialize a field value
     */
    public GsonCoder(@NonNull Gson gson) {
        mGson = gson;
    }

    /**
     * Getter for {@link Gson} instance to access it in a {@link GsonCoder} subclass.
     *
     * @return the {@link Gson} instance passed in the constructor
     */
    protected Gson gson() {
        return mGson;
    }
}