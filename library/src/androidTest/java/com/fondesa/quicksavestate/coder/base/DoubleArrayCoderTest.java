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
public class DoubleArrayCoderTest {
    private static final String COMMON_KEY = "x";

    private DoubleArrayCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new DoubleArrayCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        coder.serialize(bundle, COMMON_KEY, expected);
        assertEquals(expected, bundle.getDoubleArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        bundle.putDoubleArray(COMMON_KEY, expected);
        assertEquals(expected, coder.deserialize(bundle, COMMON_KEY));
    }

    private double[] generateArrayAndFill() {
        double[] array = new double[300];
        Arrays.fill(array, 9.0);
        return array;
    }
}