package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@SuppressWarnings("ConstantConditions")
public class BooleanCoderTest {
    private static final String COMMON_KEY = "x";

    private BooleanCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new BooleanCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeBooleanPrimitive() {
        boolean expectedValue = true;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getBoolean(COMMON_KEY));
    }

    @Test
    public void testSerializeBooleanObject() {
        Boolean expectedValue = true;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, ((Boolean) bundle.getBoolean(COMMON_KEY)));
    }

    @Test
    public void testDeserializeBooleanPrimitive() {
        boolean expectedValue = true;
        bundle.putBoolean(COMMON_KEY, expectedValue);
        assertEquals((Boolean) expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeBooleanObject() {
        Boolean expectedValue = true;
        bundle.putBoolean(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}