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
public class LongCoderTest {
    private static final String COMMON_KEY = "x";

    private LongCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new LongCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeLongPrimitive() {
        long expectedValue = 9L;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getLong(COMMON_KEY));
    }

    @Test
    public void testSerializeLongObject() {
        Long expectedValue = 9L;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, ((Long) bundle.getLong(COMMON_KEY)));
    }

    @Test
    public void testDeserializeLongPrimitive() {
        long expectedValue = 9L;
        bundle.putLong(COMMON_KEY, expectedValue);
        assertEquals((Long) expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeLongObject() {
        Long expectedValue = 9L;
        bundle.putLong(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}