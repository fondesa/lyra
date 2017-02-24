package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.coder.utils.StateCoderUtils;
import com.fondesa.quicksavestate.exception.CoderNotFoundException;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by antoniolig on 17/02/17.
 */
final class SaveStateProcessor {
    private ArrayMap<Class<?>, StateCoder<?>> mNativeCachedState;
    private ArrayMap<Class<?>, Field[]> mCachedFields;

    public SaveStateProcessor() {
        mNativeCachedState = new ArrayMap<>();
        mCachedFields = new ArrayMap<>();
    }

    public void saveState(@NonNull Object stateHolder, @NonNull Bundle bundle) {
        Class<?> originalClass = stateHolder.getClass();
        Class<?> currentClass = originalClass;
        Field[] cachedFields = mCachedFields.get(currentClass);
        if (cachedFields == null) {
            List<Field> futureCachedFields = new LinkedList<>();
            do {
                readFromFields(currentClass.getDeclaredFields(), stateHolder, bundle, futureCachedFields);
            } while ((currentClass = currentClass.getSuperclass()) != null);
            mCachedFields.put(originalClass, futureCachedFields.toArray(new Field[futureCachedFields.size()]));
        } else {
            readFromFields(cachedFields, stateHolder, bundle, null);
        }
    }

    public void restoreState(@NonNull Object stateHolder, @Nullable Bundle bundle) {
        if (bundle == null)
            return;

        Class<?> originalClass = stateHolder.getClass();
        Class<?> currentClass = originalClass;
        Field[] cachedFields = mCachedFields.get(currentClass);
        if (cachedFields == null) {
            List<Field> futureCachedFields = new LinkedList<>();
            do {
                writeToFields(currentClass.getDeclaredFields(), stateHolder, bundle, futureCachedFields);
            } while ((currentClass = currentClass.getSuperclass()) != null);
            mCachedFields.put(originalClass, futureCachedFields.toArray(new Field[futureCachedFields.size()]));
        } else {
            writeToFields(cachedFields, stateHolder, bundle, null);
        }
    }

    private void readFromFields(@NonNull Field[] fields,
                                @NonNull Object stateHolder,
                                @NonNull Bundle bundle,
                                @Nullable List<Field> listToPopulate) {

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            SaveState saveState = field.getAnnotation(SaveState.class);
            if (saveState == null)
                continue;

            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }

            final String fieldName = field.getName();
            final Object fieldValue;
            try {
                fieldValue = field.get(stateHolder);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot access to field " + fieldName +
                        " of class " + stateHolder.getClass().getName());
            }

            if (fieldValue != null) {
                final StateCoder stateCoder = getStateCoder(field, saveState);
                //noinspection unchecked
                stateCoder.serialize(bundle, fieldName, fieldValue);
            }

            if (!accessible) {
                field.setAccessible(false);
            }

            if (listToPopulate != null) {
                listToPopulate.add(field);
            }
        }
    }

    private void writeToFields(@NonNull Field[] fields,
                               @NonNull Object stateHolder,
                               @NonNull Bundle bundle,
                               @Nullable List<Field> listToPopulate) {

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            SaveState saveState = field.getAnnotation(SaveState.class);
            if (saveState == null)
                continue;

            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }

            final StateCoder stateCoder = getStateCoder(field, saveState);

            final String fieldName = field.getName();
            final Object fieldValue = stateCoder.deserialize(bundle, fieldName);
            if (fieldValue != null) {
                try {
                    field.set(stateHolder, fieldValue);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot access to field " + fieldName +
                            " of class " + stateHolder.getClass().getName());
                }
            }

            if (!accessible) {
                field.setAccessible(false);
            }

            if (listToPopulate != null) {
                listToPopulate.add(field);
            }
        }
    }


    private StateCoder getStateCoder(@NonNull Field field, @NonNull SaveState saveState) {
        final Class<?> fieldClass = field.getType();
        StateCoder stateCoder;

        final Class<? extends StateCoder> stateSDClass = saveState.value();
        if (stateSDClass == StateCoder.class) {
            stateCoder = mNativeCachedState.get(fieldClass);
            if (stateCoder != null)
                return stateCoder;

            try {
                stateCoder = StateCoderUtils.getBaseCoderForClass(fieldClass);
                mNativeCachedState.put(fieldClass, stateCoder);
            } catch (CoderNotFoundException e) {
                throw new RuntimeException("Cannot get coder for class " + fieldClass, e);
            }
        } else {
            try {
                stateCoder = stateSDClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Cannot instantiate a " + StateCoder.class.getSimpleName() +
                        " of class " + stateSDClass.getName());
            }
        }
        return stateCoder;
    }
}