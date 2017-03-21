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

package com.fondesa.quicksavestate;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.fondesa.quicksavestate.annotation.SaveState;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by antoniolig on 01/03/17.
 */
public class DefaultFieldsRetriever implements FieldsRetriever {
    private ArrayMap<Class<?>, Field[]> mCachedFields;

    public DefaultFieldsRetriever() {
        mCachedFields = new ArrayMap<>();
    }

    public Field[] getFields(@NonNull Class<?> cls) {
        Field[] cachedFields = mCachedFields.get(cls);
        if (cachedFields != null)
            return cachedFields;

        Class<?> currentClass = cls;
        List<Field> futureCachedFields = new LinkedList<>();
        do {
            Field[] declaredFields = currentClass.getDeclaredFields();
            for (int i = 0; i < currentClass.getDeclaredFields().length; i++) {
                Field field = declaredFields[i];
                Annotation annotation = field.getAnnotation(SaveState.class);
                if (annotation == null)
                    continue;

                futureCachedFields.add(field);
            }
        } while ((currentClass = currentClass.getSuperclass()) != null);

        cachedFields = futureCachedFields.toArray(new Field[futureCachedFields.size()]);
        mCachedFields.put(cls, cachedFields);
        return cachedFields;
    }
}