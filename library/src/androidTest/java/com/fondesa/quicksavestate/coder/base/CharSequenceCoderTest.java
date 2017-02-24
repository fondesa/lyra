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
public class CharSequenceCoderTest {
    private static final String COMMON_KEY = "x";

    private CharSequenceCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new CharSequenceCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeCharSequence() {
        CharSequence expectedValue = "test";
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getCharSequence(COMMON_KEY));
    }

    @Test
    public void testDeserializeCharSequence() {
        CharSequence expectedValue = "test";
        bundle.putCharSequence(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}