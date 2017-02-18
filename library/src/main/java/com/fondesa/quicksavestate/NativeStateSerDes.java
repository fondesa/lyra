package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 18/02/17.
 */

public class NativeStateSerDes implements StateSerDes<Object> {
    @Override
    public void serialize(@NonNull StateBundleManager manager, @NonNull String fieldKey, @NonNull Object fieldValue) {
        if (fieldValue instanceof String) {
            manager.putString(fieldKey, (String) fieldValue);
        }
    }

    @Override
    public Object deserialize(@NonNull StateBundleManager manager, @NonNull String fieldName) {
        return manager.readValue(fieldName);
    }
}
