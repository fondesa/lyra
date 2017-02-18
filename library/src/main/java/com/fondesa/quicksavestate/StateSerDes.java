package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 17/02/17.
 */

public interface StateSerDes<T> {
    void serialize(@NonNull StateBundleManager manager, @NonNull String fieldName, @NonNull T fieldValue);

    T deserialize(@NonNull StateBundleManager manager, @NonNull String fieldName);
}
