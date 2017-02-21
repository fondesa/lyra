package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 22/02/17.
 */
public class IntCoder extends BaseCoder<Integer> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Integer fieldValue) {
        state.putInt(fieldName, fieldValue);
    }
}