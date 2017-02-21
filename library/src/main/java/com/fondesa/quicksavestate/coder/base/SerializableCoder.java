package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by antoniolig on 22/02/17.
 */
public class SerializableCoder extends BaseCoder<Serializable> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Serializable fieldValue) {
        state.putSerializable(fieldName, fieldValue);
    }
}