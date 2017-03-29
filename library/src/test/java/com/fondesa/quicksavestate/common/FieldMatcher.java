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

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.lang.reflect.Field;

/**
 * Custom matcher to match a group of {@link Field} with their names.
 */
public class FieldMatcher extends BaseMatcher<Field[]> {
    private String[] mFieldsName;
    private boolean mMatchExactly;

    /**
     * Creates a new instance of {@link FieldMatcher} to check if each name passed as parameter
     * matches at least one {@link Field}.
     *
     * @param fieldsName varargs of java fields
     * @return a new instance of {@link FieldMatcher}
     */
    public static FieldMatcher containNames(@NonNull String... fieldsName) {
        return new FieldMatcher(fieldsName, false);
    }

    /**
     * Creates a new instance of {@link FieldMatcher} to check if all compared fields have
     * names equals to those passed as parameter.
     *
     * @param fieldsName varargs of java fields
     * @return a new instance of {@link FieldMatcher}
     */
    public static FieldMatcher haveNames(@NonNull String... fieldsName) {
        return new FieldMatcher(fieldsName, true);
    }

    private FieldMatcher(@NonNull String[] fieldsName, boolean matchExactly) {
        mFieldsName = fieldsName;
        mMatchExactly = matchExactly;
    }

    @Override
    public boolean matches(Object item) {
        if (item == null || !(item instanceof Field[]))
            return false;

        Field[] fields = (Field[]) item;
        int contained = 0;
        for (String fieldName : mFieldsName) {
            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    contained++;
                    break;
                }
            }
        }

        if (mMatchExactly) {
            return contained == fields.length;
        } else {
            return contained == mFieldsName.length;
        }
    }

    @Override
    public void describeTo(Description description) {
        /* empty */
    }
}