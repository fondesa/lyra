package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class DoubleArrayCoderTest {
    @Rule
    public final CoderRule<DoubleArrayCoder> mCoderRule = new CoderRule<>(DoubleArrayCoder.class);

    @Test
    public void testSerializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getDoubleArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        mCoderRule.bundle().putDoubleArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private double[] generateArrayAndFill() {
        double[] array = new double[300];
        Arrays.fill(array, 9.0);
        return array;
    }
}