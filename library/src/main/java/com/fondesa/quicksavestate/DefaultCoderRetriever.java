package com.fondesa.quicksavestate;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.coder.utils.StateCoderUtils;
import com.fondesa.quicksavestate.exception.CoderNotFoundException;

/**
 * Created by antoniolig on 01/03/17.
 */
public class DefaultCoderRetriever implements CoderRetriever {
    private ArrayMap<Class<?>, StateCoder<?>> mCachedCoders;

    public DefaultCoderRetriever() {
        mCachedCoders = new ArrayMap<>();
    }

    @Override
    public StateCoder getCoder(@NonNull SaveState saveState, @NonNull Class<?> annotatedFieldClass) {
        StateCoder stateCoder;

        final Class<? extends StateCoder> stateSDClass = saveState.value();
        if (stateSDClass == StateCoder.class) {
            stateCoder = mCachedCoders.get(annotatedFieldClass);
            if (stateCoder != null)
                return stateCoder;

            try {
                stateCoder = StateCoderUtils.getBasicCoderForClass(annotatedFieldClass);
                mCachedCoders.put(annotatedFieldClass, stateCoder);
            } catch (CoderNotFoundException e) {
                throw new RuntimeException("Cannot get coder for class " + annotatedFieldClass, e);
            }
        } else {
            try {
                stateCoder = stateSDClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Cannot instantiate a " + StateCoder.class.getSimpleName() +
                        " of class " + stateSDClass.getName());
            }
        }
        return stateCoder;
    }
}
