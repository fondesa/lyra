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
public class DoubleCoderTest {
    private static final String COMMON_KEY = "x";

    private DoubleCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new DoubleCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeDoublePrimitive() {
        double expectedValue = 9.0;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getDouble(COMMON_KEY), 0);
    }

    @Test
    public void testSerializeDoubleObject() {
        Double expectedValue = 9.0;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getDouble(COMMON_KEY));
    }

    @Test
    public void testDeserializeDoublePrimitive() {
        double expectedValue = 9.0;
        bundle.putDouble(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY), 0);
    }

    @Test
    public void testDeserializeDoubleObject() {
        Double expectedValue = 9.0;
        bundle.putDouble(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}