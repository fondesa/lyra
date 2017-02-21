package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.quicksavestate.coder.StateCoder;

/**
 * Created by antoniolig on 22/02/17.
 */
abstract class BaseCoder<FieldType> implements StateCoder<FieldType> {
    @Override
    public FieldType deserialize(@NonNull Bundle manager, @NonNull String fieldName) {
        //noinspection unchecked
        return (FieldType) manager.get(fieldName);
    }
}