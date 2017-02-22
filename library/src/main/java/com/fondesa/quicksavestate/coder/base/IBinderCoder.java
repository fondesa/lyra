package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.BundleCompat;

/**
 * Created by antoniolig on 22/02/17.
 */
public class IBinderCoder extends BaseCoder<IBinder> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull IBinder fieldValue) {
        BundleCompat.putBinder(state, fieldName, fieldValue);
    }
}
