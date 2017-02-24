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
public class ShortArrayCoderTest {
    @Rule
    public CoderRule<ShortArrayCoder> coderRule = new CoderRule<ShortArrayCoder>() {
        @Override
        protected ShortArrayCoder initCoder() {
            return new ShortArrayCoder();
        }
    };

    @Test
    public void testSerializeShortArray() {
        short[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getShortArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeShortArray() {
        short[] expected = generateArrayAndFill();
        coderRule.bundle.putShortArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private short[] generateArrayAndFill() {
        short[] array = new short[300];
        Arrays.fill(array, (short) 9);
        return array;
    }
}