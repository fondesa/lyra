package com.fondesa.quicksavestate;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

/**
 * Created by antoniolig on 18/02/17.
 */

public class NativeSD {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static class BinderSD extends BaseSD<IBinder> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull IBinder fieldValue) {
            state.putBinder(fieldName, fieldValue);
        }
    }


    public static class StringSD extends BaseSD<String> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull String fieldValue) {
            state.putString(fieldName, fieldValue);
        }
    }


    private abstract static class BaseSD<T> implements StateSD<T> {
        @Override
        public T deserialize(@NonNull Bundle manager, @NonNull String fieldName) {
            //noinspection unchecked
            return (T) manager.get(fieldName);
        }
    }
}