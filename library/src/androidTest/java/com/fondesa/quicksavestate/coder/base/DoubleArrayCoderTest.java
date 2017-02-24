package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
public class DoubleArrayCoderTest {
    @Rule
    public final CoderRule<DoubleArrayCoder> coderRule = new CoderRule<DoubleArrayCoder>() {
        @Override
        protected DoubleArrayCoder initCoder() {
            return new DoubleArrayCoder();
        }
    };

    @Test
    public void testSerializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getDoubleArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        coderRule.bundle.putDoubleArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private double[] generateArrayAndFill() {
        double[] array = new double[300];
        Arrays.fill(array, 9.0);
        return array;
    }
}