package com.fondesa.quicksavestate.coder;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 17/02/17.
 */
public interface StateCoder<FieldType> {
    void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull FieldType fieldValue);

    FieldType deserialize(@NonNull Bundle state, @NonNull String fieldName);
}