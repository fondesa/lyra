package com.fondesa.quicksavestate.coder.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.SizeF;

/**
 * Created by antoniolig on 22/02/17.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SizeFCoder extends BaseCoder<SizeF> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull SizeF fieldValue) {
        state.putSizeF(fieldName, fieldValue);
    }
}