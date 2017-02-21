package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 22/02/17.
 */
public class FloatCoder extends BaseCoder<Float> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Float fieldValue) {
        state.putFloat(fieldName, fieldValue);
    }
}