package com.fondesa.quicksavestate.testhelper.util;

import android.os.Build;

import org.robolectric.util.ReflectionHelpers;

public class TestUtil {
    private TestUtil() {
        // private empty constructor to avoid instantiation
    }

    public static void setApiVersion(int apiVersion) {
        ReflectionHelpers.setStaticField(Build.VERSION.class, "SDK_INT", apiVersion);
    }
}