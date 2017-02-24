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
public class ShortArrayCoderTest {
    private static final String COMMON_KEY = "x";

    private ShortArrayCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new ShortArrayCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeShortArray() {
        short[] expected = generateArrayAndFill();
        coder.serialize(bundle, COMMON_KEY, expected);
        assertEquals(expected, bundle.getShortArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeShortArray() {
        short[] expected = generateArrayAndFill();
        bundle.putShortArray(COMMON_KEY, expected);
        assertEquals(expected, coder.deserialize(bundle, COMMON_KEY));
    }

    private short[] generateArrayAndFill() {
        short[] array = new short[300];
        Arrays.fill(array, (short) 9);
        return array;
    }
}