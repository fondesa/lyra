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
public class StringArrayCoderTest {
    private static final String COMMON_KEY = "x";

    private StringArrayCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new StringArrayCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeStringArray() {
        String[] expected = generateArrayAndFill();
        coder.serialize(bundle, COMMON_KEY, expected);
        assertEquals(expected, bundle.getStringArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeStringArray() {
        String[] expected = generateArrayAndFill();
        bundle.putStringArray(COMMON_KEY, expected);
        assertEquals(expected, coder.deserialize(bundle, COMMON_KEY));
    }

    private String[] generateArrayAndFill() {
        String[] array = new String[300];
        Arrays.fill(array, "test");
        return array;
    }
}