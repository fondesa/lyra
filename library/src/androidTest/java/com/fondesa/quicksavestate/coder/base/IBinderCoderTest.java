package com.fondesa.quicksavestate.coder.base;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.BundleCompat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@SuppressWarnings("ConstantConditions")
public class IBinderCoderTest {
    private static final String COMMON_KEY = "x";

    private IBinderCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new IBinderCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeIBinder() {
        IBinder expectedValue = new Binder();
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, BundleCompat.getBinder(bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeIBinder() {
        IBinder expectedValue = new Binder();
        BundleCompat.putBinder(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}