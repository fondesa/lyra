package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 22/02/17.
 */
public class ByteArrayCoder extends BaseCoder<byte[]> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull byte[] fieldValue) {
        state.putByteArray(fieldName, fieldValue);
    }
}