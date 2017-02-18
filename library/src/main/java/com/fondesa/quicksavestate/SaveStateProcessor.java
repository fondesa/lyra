package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Field;

/**
 * Created by antoniolig on 17/02/17.
 */

public class SaveStateProcessor<StateHolder> {
    public SaveStateProcessor() {
    }

    public void saveState(@NonNull StateHolder stateHolder, @NonNull Bundle bundle) {
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

            String fieldName = field.getName();
            Object fieldValue;
            try {
                fieldValue = field.get(stateHolder);
            } catch (IllegalAccessException e) {
                fieldValue = null;
            }

            if (fieldValue != null) {
                StateBundleManager bundleManager = new StateBundleManager(bundle);
                StateSerDes stateSerDes = getStateSerDes(saveState);
                stateSerDes.serialize(bundleManager, fieldName, fieldValue);
            }

            if (!accessible) {
                field.setAccessible(false);
            }
        }
    }

    public void restoreState(@NonNull StateHolder stateHolder, @Nullable Bundle bundle) {
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

            StateBundleManager bundleManager = new StateBundleManager(bundle);
            final StateSerDes stateSerDes = getStateSerDes(saveState);
            Object fieldValue = stateSerDes.deserialize(bundleManager, field.getName());

            if (fieldValue != null) {
                try {
                    field.set(stateHolder, fieldValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (!accessible) {
                field.setAccessible(false);
            }
        }
    }

    private StateSerDes getStateSerDes(@NonNull SaveState saveState) {
        Class<? extends StateSerDes> stateSerDesClass = saveState.value();
        StateSerDes stateSerDes = null;
        if (stateSerDesClass == StateSerDes.class) {
            stateSerDes = new NativeStateSerDes();
        } else {
            try {
                stateSerDes = stateSerDesClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stateSerDes;
    }
}