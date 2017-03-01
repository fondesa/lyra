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
    public final CoderRule<DoubleArrayCoder> coderRule = new CoderRule<>(DoubleArrayCoder.class);

    @Test
    public void testSerializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expected);
        assertEquals(expected, coderRule.bundle.getDoubleArray(coderRule.randomKey));
    }

    @Test
    public void testDeserializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        coderRule.bundle.putDoubleArray(coderRule.randomKey, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    private double[] generateArrayAndFill() {
        double[] array = new double[300];
        Arrays.fill(array, 9.0);
        return array;
    }
}