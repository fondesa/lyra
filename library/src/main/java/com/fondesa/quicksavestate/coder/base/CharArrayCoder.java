package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 22/02/17.
 */
public class CharArrayCoder extends BaseCoder<char[]> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull char[] fieldValue) {
        state.putCharArray(fieldName, fieldValue);
    }
}