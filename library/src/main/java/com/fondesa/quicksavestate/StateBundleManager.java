package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by antoniolig on 17/02/17.
 */

public class StateBundleManager {
    private static final String BUNDLE_PREFIX = "ots";

    private Bundle mBundle;

    public StateBundleManager(@NonNull Bundle bundle) {
        mBundle = bundle;
    }

    public void putString(@NonNull String key, @Nullable String value) {
        mBundle.putString(fullKey(key), value);
    }

    public void putValue(@NonNull String fieldName, @Nullable Object fieldValue) {
//
    }

    @Nullable
    public Object readValue(@NonNull String fieldName) {
        return mBundle.get(fullKey(fieldName));
    }

    private static String fullKey(@NonNull String fieldName) {
        return BUNDLE_PREFIX + fieldName;
    }
}
