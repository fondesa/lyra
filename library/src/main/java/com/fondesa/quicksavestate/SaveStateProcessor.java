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

/**
 * Created by antoniolig on 17/02/17.
 */
final class SaveStateProcessor {
    private ArrayMap<Class<?>, StateCoder<?>> mNativeCachedState;

    public SaveStateProcessor() {
        mNativeCachedState = new ArrayMap<>();
    }

    public void saveState(@NonNull Object stateHolder, @NonNull Bundle bundle) {
        Field[] fields = stateHolder.getClass().getDeclaredFields();
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
        }
    }

    public void restoreState(@NonNull Object stateHolder, @Nullable Bundle bundle) {
        if (bundle == null)
            return;

        Field[] fields = stateHolder.getClass().getDeclaredFields();
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