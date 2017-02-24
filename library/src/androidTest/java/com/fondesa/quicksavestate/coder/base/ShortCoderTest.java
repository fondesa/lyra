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
public class ShortCoderTest {
    private static final String COMMON_KEY = "x";

    private ShortCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new ShortCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeShortPrimitive() {
        short expectedValue = 9;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getShort(COMMON_KEY));
    }

    @Test
    public void testSerializeShortObject() {
        Short expectedValue = 9;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, (Short) bundle.getShort(COMMON_KEY));
    }

    @Test
    public void testDeserializeShortPrimitive() {
        short expectedValue = 9;
        bundle.putShort(COMMON_KEY, expectedValue);
        assertEquals((Short) expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeShortObject() {
        Short expectedValue = 9;
        bundle.putShort(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}