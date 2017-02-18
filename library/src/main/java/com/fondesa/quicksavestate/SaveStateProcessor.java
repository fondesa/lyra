package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import java.lang.reflect.Field;

/**
 * Created by antoniolig on 17/02/17.
 */

public class SaveStateProcessor {
    private ArrayMap<Class<?>, StateSD<?>> mNativeCachedState;

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
                final StateSD stateSD = getStateSD(field, saveState);
                stateSD.serialize(bundle, fieldName, fieldValue);
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

            final StateSD stateSD = getStateSD(field, saveState);

            final String fieldName = field.getName();
            final Object fieldValue = stateSD.deserialize(bundle, fieldName);
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

    private StateSD getStateSD(@NonNull Field field, @NonNull SaveState saveState) {
        final Class<?> fieldClass = field.getType();
        StateSD stateSD = mNativeCachedState.get(fieldClass);
        if (stateSD != null)
            return stateSD;

        final Class<? extends StateSD> stateSDClass = saveState.value();
        if (stateSDClass == StateSD.class) {
            stateSD = getNativeStateSD(fieldClass);
            if (stateSD == null) {
                throw new RuntimeException("You have to specify a custom " + StateSD.class.getName() +
                        " for an object of type " + fieldClass.getClass().getName());
            }
            mNativeCachedState.put(fieldClass, stateSD);
        } else {
            try {
                stateSD = stateSDClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Cannot instantiate a " + StateSD.class.getSimpleName() +
                        " of class " + stateSDClass.getClass().getName());
            }
        }
        return stateSD;
    }

    private StateSD getNativeStateSD(Class<?> value) {
        if (value.isAssignableFrom(String.class))
            return new NativeSD.StringSD();

        return null;
    }
}