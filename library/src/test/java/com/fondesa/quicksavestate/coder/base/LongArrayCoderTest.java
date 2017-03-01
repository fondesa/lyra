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
public class LongArrayCoderTest {
    @Rule
    public final CoderRule<LongArrayCoder> coderRule = new CoderRule<>(LongArrayCoder.class);

    @Test
    public void testSerializeLongArray() {
        long[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expected);
        assertEquals(expected, coderRule.bundle.getLongArray(coderRule.randomKey));
    }

    @Test
    public void testDeserializeLongArray() {
        long[] expected = generateArrayAndFill();
        coderRule.bundle.putLongArray(coderRule.randomKey, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    private long[] generateArrayAndFill() {
        long[] array = new long[300];
        Arrays.fill(array, 9L);
        return array;
    }
}