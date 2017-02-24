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
public class CharArrayCoderTest {
    private static final String COMMON_KEY = "x";

    private CharArrayCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new CharArrayCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeCharArray() {
        char[] expected = generateArrayAndFill();
        coder.serialize(bundle, COMMON_KEY, expected);
        assertEquals(expected, bundle.getCharArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeCharArray() {
        char[] expected = generateArrayAndFill();
        bundle.putCharArray(COMMON_KEY, expected);
        assertEquals(expected, coder.deserialize(bundle, COMMON_KEY));
    }

    private char[] generateArrayAndFill() {
        char[] array = new char[300];
        Arrays.fill(array, 'x');
        return array;
    }
}