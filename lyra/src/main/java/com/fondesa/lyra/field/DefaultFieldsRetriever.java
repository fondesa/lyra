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

package com.fondesa.lyra.field;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.fondesa.lyra.annotation.SaveState;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Default implementation of {@link FieldsRetriever} that handles the cache for retrieved fields.
 * <br>
 * The fields are retrieved from the full list of fields of the class and each superclass.
 * In this implementation, all visibility modifiers in fields are accepted.
 * <br>
 * The class used as key for the cache, will be the class passed to the method {@link #getFields(Class)}.
 * This means that referring to A, B and C as three classes with fields annotated with {@link SaveState},
 * if A extends B and C extends B, the classes inserted in cache are A and C.
 */
public class DefaultFieldsRetriever implements FieldsRetriever {
    private ArrayMap<Class<?>, Field[]> mCachedFields;

    /**
     * Creates a new instance of {@link DefaultFieldsRetriever} and initialize the cache map.
     */
    public DefaultFieldsRetriever() {
        mCachedFields = new ArrayMap<>();
    }

    /**
     * Retrieve an array of {@link Field} from a class.
     *
     * @param cls java class containing the fields that will be saved
     * @return array of {@link Field} annotated with {@link SaveState}
     */
    public Field[] getFields(@NonNull Class<?> cls) {
        Field[] cachedFields = mCachedFields.get(cls);
        // If the cache is valid, fields will be returned.
        if (cachedFields != null)
            return cachedFields;

        Class<?> currentClass = cls;
        final List<Field> futureCachedFields = new LinkedList<>();
        do {
            Field[] declaredFields = currentClass.getDeclaredFields();
            for (int i = 0; i < currentClass.getDeclaredFields().length; i++) {
                final Field field = declaredFields[i];
                // If the field isn't annotated with @SaveState, the loop will continue to the next field.
                final Annotation annotation = field.getAnnotation(SaveState.class);
                if (annotation == null)
                    continue;

                futureCachedFields.add(field);
            }
        } while // Loop again if there's a superclass of this class.
                ((currentClass = currentClass.getSuperclass()) != null);

        // Create the array from the list.
        cachedFields = futureCachedFields.toArray(new Field[futureCachedFields.size()]);
        // Put the fields in cache.
        mCachedFields.put(cls, cachedFields);
        return cachedFields;
    }
}