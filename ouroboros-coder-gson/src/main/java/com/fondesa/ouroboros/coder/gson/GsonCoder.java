package com.fondesa.ouroboros.coder.gson;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.ouroboros.coder.StateCoder;
import com.google.gson.Gson;

public class GsonCoder implements StateCoder {

    @Override
    public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Object fieldValue) {
        Gson gson = new Gson();
    }

    @Override
    public Object deserialize(@NonNull Bundle state, @NonNull String fieldName) {
        return null;
    }
}