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
public class CharCoderTest {
    private static final String COMMON_KEY = "x";

    private CharCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new CharCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeCharPrimitive() {
        char expectedValue = 'x';
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getChar(COMMON_KEY));
    }

    @Test
    public void testSerializeCharObject() {
        Character expectedValue = 'x';
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, ((Character) bundle.getChar(COMMON_KEY)));
    }

    @Test
    public void testDeserializeCharPrimitive() {
        char expectedValue = 'x';
        bundle.putChar(COMMON_KEY, expectedValue);
        assertEquals((Character) expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeCharObject() {
        Character expectedValue = 'x';
        bundle.putChar(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}