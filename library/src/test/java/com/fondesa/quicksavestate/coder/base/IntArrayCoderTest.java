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
public class IntArrayCoderTest {
    @Rule
    public final CoderRule<IntArrayCoder> mCoderRule = new CoderRule<>(IntArrayCoder.class);

    @Test
    public void testSerializeIntArray() {
        int[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getIntArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeIntArray() {
        int[] expected = generateArrayAndFill();
        mCoderRule.bundle().putIntArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private int[] generateArrayAndFill() {
        int[] array = new int[300];
        Arrays.fill(array, 9);
        return array;
    }
}