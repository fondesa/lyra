package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 22/02/17.
 */
public class DoubleCoder extends BaseCoder<Double> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Double fieldValue) {
        state.putDouble(fieldName, fieldValue);
    }
}