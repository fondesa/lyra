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
public class StringCoderTest {
    private static final String COMMON_KEY = "x";

    private StringCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new StringCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeString() {
        String expectedValue = "test";
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getString(COMMON_KEY));
    }

    @Test
    public void testDeserializeString() {
        String expectedValue = "test";
        bundle.putString(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}