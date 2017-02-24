package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
public class ByteArrayCoderTest {
    private static final String COMMON_KEY = "x";

    private ByteArrayCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new ByteArrayCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeByteArray() {
        byte[] expected = generateArrayAndFill();
        coder.serialize(bundle, COMMON_KEY, expected);
        assertEquals(expected, bundle.getByteArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeByteArray() {
        byte[] expected = generateArrayAndFill();
        bundle.putByteArray(COMMON_KEY, expected);
        assertEquals(expected, coder.deserialize(bundle, COMMON_KEY));
    }

    private byte[] generateArrayAndFill() {
        byte[] array = new byte[300];
        Arrays.fill(array, (byte) 2);
        return array;
    }
}