package com.fondesa.quicksavestate;

import android.support.annotation.NonNull;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;

/**
 * Created by antoniolig on 01/03/17.
 */
public interface CoderRetriever {
    StateCoder getCoder(@NonNull SaveState saveState, @NonNull Class<?> annotatedFieldClass);
}
