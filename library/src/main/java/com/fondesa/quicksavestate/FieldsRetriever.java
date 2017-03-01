package com.fondesa.quicksavestate;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;

/**
 * Created by antoniolig on 01/03/17.
 */
public interface FieldsRetriever {
    Field[] getFields(@NonNull Class<?> cls);
}