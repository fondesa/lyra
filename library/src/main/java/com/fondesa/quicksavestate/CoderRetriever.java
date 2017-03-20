package com.fondesa.quicksavestate;

import android.support.annotation.NonNull;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.exception.CoderNotFoundException;

/**
 * Created by antoniolig on 01/03/17.
 */
public interface CoderRetriever {
    @NonNull
    StateCoder getCoder(@NonNull SaveState saveState, @NonNull Class<?> annotatedFieldClass) throws CoderNotFoundException;
}
