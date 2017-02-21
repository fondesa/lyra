package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 22/02/17.
 */
public class ByteCoder extends BaseCoder<Byte> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Byte fieldValue) {
        state.putByte(fieldName, fieldValue);
    }
}