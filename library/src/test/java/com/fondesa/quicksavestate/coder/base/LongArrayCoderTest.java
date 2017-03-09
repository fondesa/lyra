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
    public final CoderRule<LongArrayCoder> mCoderRule = new CoderRule<>(LongArrayCoder.class);

    @Test
    public void testSerializeLongArray() {
        long[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getLongArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeLongArray() {
        long[] expected = generateArrayAndFill();
        mCoderRule.bundle().putLongArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private long[] generateArrayAndFill() {
        long[] array = new long[300];
        Arrays.fill(array, 9L);
        return array;
    }
}