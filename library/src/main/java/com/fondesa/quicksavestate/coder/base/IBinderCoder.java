package com.fondesa.quicksavestate.coder.base;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

/**
 * Created by antoniolig on 22/02/17.
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class IBinderCoder extends BaseCoder<IBinder> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull IBinder fieldValue) {
        state.putBinder(fieldName, fieldValue);
    }
}
