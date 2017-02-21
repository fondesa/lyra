package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 22/02/17.
 */
public class ParcelableCoder extends BaseCoder<Parcelable> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Parcelable fieldValue) {
        state.putParcelable(fieldName, fieldValue);
    }
}