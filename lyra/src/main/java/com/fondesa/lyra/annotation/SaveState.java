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

package com.fondesa.lyra.annotation;

import android.os.Bundle;

import com.fondesa.lyra.coder.StateCoder;
import com.fondesa.lyra.coder.utils.StateCoderUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to annotate the fields that you want to save/restore.
 * You can pass a custom {@link StateCoder} to save/restore a field, otherwise,
 * a default {@link StateCoder} will be used.
 * <br>
 * The possibilities are:
 * <ul>
 * <li>{@code @StateCoder}: StateCoder.class</li>
 * <li>{@code @StateCoder(CustomCoder.class)}: CustomCoder.class</li>
 * <li>{@code @StateCoder(value = CustomCoder.class)}: CustomCoder.class</li>
 * </ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SaveState {

    /**
     * Specify the coder to serialize the field into a {@link Bundle} and to
     * deserialize it from a {@link Bundle}.
     * The default coder is {@link StateCoder} without a real implementation.
     * The right default coder will be chosen runtime with {@link StateCoderUtils#getBasicCoderForClass(Class)}.
     *
     * @return concrete instance of {@link StateCoder} to use when a field must be serialized/deserialized
     */
    Class<? extends StateCoder> value() default StateCoder.class;
}