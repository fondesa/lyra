package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 17/02/17.
 */

public interface StateSD<T> {
    void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull T fieldValue);

    T deserialize(@NonNull Bundle state, @NonNull String fieldName);
}
