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
public class BooleanArrayCoderTest {
    @Rule
    public final CoderRule<BooleanArrayCoder> coderRule = new CoderRule<BooleanArrayCoder>() {
        @Override
        protected BooleanArrayCoder initCoder() {
            return new BooleanArrayCoder();
        }
    };

    @Test
    public void testSerializeBooleanArray() {
        boolean[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getBooleanArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeBooleanArray() {
        boolean[] expected = generateArrayAndFill();
        coderRule.bundle.putBooleanArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private boolean[] generateArrayAndFill() {
        boolean[] array = new boolean[300];
        Arrays.fill(array, true);
        return array;
    }
}