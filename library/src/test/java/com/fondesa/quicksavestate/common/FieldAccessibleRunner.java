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

package com.fondesa.quicksavestate.common;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;

/**
 * Abstract class used to run a code block when a {@link Field} is accessible.
 * If a {@link Field} isn't accessible, it will be modified in accessible and it will
 * return with its original visibility modifier after the execution of the method {@link #runWithField(Field)}.
 */
public abstract class FieldAccessibleRunner {

    /**
     * Creates a new instance of {@link FieldAccessibleRunner} for a given {@link Field}.
     *
     * @param field instance of {@link Field} that must be modified in accessible
     * @throws Exception if the {@link Field} can't be modified in accessible
     */
    public FieldAccessibleRunner(@NonNull Field field) throws Exception {
        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        // Run the code block with an accessible field.
        runWithField(field);
        if (!accessible) {
            field.setAccessible(false);
        }
    }

    /**
     * Run a block of code with an accessible {@link Field}.
     *
     * @param field the accessible {@link Field} instance
     * @throws Exception if you want to block the test method's execution
     */
    protected abstract void runWithField(@NonNull Field field) throws Exception;
}