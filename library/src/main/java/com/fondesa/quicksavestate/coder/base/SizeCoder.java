package com.fondesa.quicksavestate.coder.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Size;

/**
 * Created by antoniolig on 22/02/17.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SizeCoder extends BaseCoder<Size> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Size fieldValue) {
        state.putSize(fieldName, fieldValue);
    }
}