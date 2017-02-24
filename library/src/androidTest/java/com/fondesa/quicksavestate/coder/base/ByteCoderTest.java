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
public class ByteCoderTest {
    private static final String COMMON_KEY = "x";

    private ByteCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new ByteCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeBytePrimitive() {
        byte expectedValue = 2;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getByte(COMMON_KEY));
    }

    @Test
    public void testSerializeByteObject() {
        Byte expectedValue = 2;
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, ((Byte) bundle.getByte(COMMON_KEY)));
    }

    @Test
    public void testDeserializeBytePrimitive() {
        byte expectedValue = 2;
        bundle.putByte(COMMON_KEY, expectedValue);
        assertEquals((Byte) expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeByteObject() {
        Byte expectedValue = 2;
        bundle.putByte(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
}