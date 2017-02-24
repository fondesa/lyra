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
public class IntArrayCoderTest {
    @Rule
    public final CoderRule<IntArrayCoder> coderRule = new CoderRule<IntArrayCoder>() {
        @Override
        protected IntArrayCoder initCoder() {
            return new IntArrayCoder();
        }
    };

    @Test
    public void testSerializeIntArray() {
        int[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getIntArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeIntArray() {
        int[] expected = generateArrayAndFill();
        coderRule.bundle.putIntArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private int[] generateArrayAndFill() {
        int[] array = new int[300];
        Arrays.fill(array, 9);
        return array;
    }
}