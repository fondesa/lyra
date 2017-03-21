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
 * Created by antoniolig on 11/03/17.
 */
public abstract class FieldAccessibleRunner {
    public FieldAccessibleRunner(@NonNull Field field) throws Exception {
        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        runWithField(field);
        if (!accessible) {
            field.setAccessible(false);
        }
    }

    protected abstract void runWithField(@NonNull Field field) throws Exception;
}