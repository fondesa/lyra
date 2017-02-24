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
public class IntCoderTest {
    private static final String COMMON_KEY = "x";

    private IntCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new IntCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeIntPrimitive() {
        int expectedValue = 9;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getInt(COMMON_KEY), 0);
    }

    @Test
    public void testSerializeIntObject() {
        Integer expectedValue = 9;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, (Integer) bundle.getInt(COMMON_KEY));
    }

    @Test
    public void testDeserializeIntPrimitive() {
        int expectedValue = 9;
        bundle.putInt(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY), 0);
    }

    @Test
    public void testDeserializeIntObject() {
        Integer expectedValue = 9;
        bundle.putInt(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}