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

package com.fondesa.ouroboros.coder.gson.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.lang.reflect.Field;

/**
 * Default implementation of {@link GsonCoder} to serialize every object with {@link Gson}.
 * Considering that {@link Gson} needs a class for the deserialize, the class of the {@link Field}
 * is passed in the {@link Bundle}.
 */
public class DefaultGsonCoder extends GsonCoder<Object> {
    private final static String CLASS_KEY = "c";
    private final static String VALUE_KEY = "v";

    /**
     * Creates a new instance of {@link DefaultGsonCoder} with a given {@link Gson} instance.
     *
     * @param gson instance of {@link Gson} used to serialize/deserialize a field value
     */
    public DefaultGsonCoder(@NonNull Gson gson) {
        super(gson);
    }

    /**
     * Write a field's value into the saved state {@link Bundle}.
     *
     * @param state      {@link Bundle} used to save the state
     * @param fieldName  name of the field
     * @param fieldValue value of field
     */
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Object fieldValue) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CLASS_KEY, fieldValue.getClass());
        bundle.putSerializable(VALUE_KEY, gson().toJson(fieldValue));
        state.putBundle(fieldName, bundle);
    }

    /**
     * Read a field's value from the saved state {@link Bundle}.
     *
     * @param state     {@link Bundle} used to save the state
     * @param fieldName name of the field
     * @return value of the field
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public Object deserialize(@NonNull Bundle state, @NonNull String fieldName) {
        Bundle bundle = state.getBundle(fieldName);
        Class jsonClass = (Class) bundle.getSerializable(CLASS_KEY);
        String jsonString = bundle.getString(VALUE_KEY);
        return gson().fromJson(jsonString, jsonClass);
    }
}