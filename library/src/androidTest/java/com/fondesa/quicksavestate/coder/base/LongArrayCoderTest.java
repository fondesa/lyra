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
public class LongArrayCoderTest {
    @Rule
    public final CoderRule<LongArrayCoder> coderRule = new CoderRule<LongArrayCoder>() {
        @Override
        protected LongArrayCoder initCoder() {
            return new LongArrayCoder();
        }
    };

    @Test
    public void testSerializeLongArray() {
        long[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getLongArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeLongArray() {
        long[] expected = generateArrayAndFill();
        coderRule.bundle.putLongArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private long[] generateArrayAndFill() {
        long[] array = new long[300];
        Arrays.fill(array, 9L);
        return array;
    }
}