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