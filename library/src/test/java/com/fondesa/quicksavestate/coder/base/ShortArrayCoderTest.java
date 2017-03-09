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
public class ShortArrayCoderTest {
    @Rule
    public final CoderRule<ShortArrayCoder> mCoderRule = new CoderRule<>(ShortArrayCoder.class);

    @Test
    public void testSerializeShortArray() {
        short[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getShortArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeShortArray() {
        short[] expected = generateArrayAndFill();
        mCoderRule.bundle().putShortArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private short[] generateArrayAndFill() {
        short[] array = new short[300];
        Arrays.fill(array, (short) 9);
        return array;
    }
}