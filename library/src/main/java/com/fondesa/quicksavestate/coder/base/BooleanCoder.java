package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 22/02/17.
 */
public class BooleanCoder extends BaseCoder<Boolean> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Boolean fieldValue) {
        state.putBoolean(fieldName, fieldValue);
    }
}