package com.fondesa.quicksavestate.common;

import android.os.Build;

import org.robolectric.util.ReflectionHelpers;

public class TestUtils {
    private TestUtils() {
        // private empty constructor to avoid instantiation
    }

    public static void setApiVersion(int apiVersion) {
        ReflectionHelpers.setStaticField(Build.VERSION.class, "SDK_INT", apiVersion);
    }
}