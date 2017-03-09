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
public class BooleanArrayCoderTest {
    @Rule
    public final CoderRule<BooleanArrayCoder> mCoderRule = new CoderRule<>(BooleanArrayCoder.class);

    @Test
    public void testSerializeBooleanArray() {
        boolean[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getBooleanArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeBooleanArray() {
        boolean[] expected = generateArrayAndFill();
        mCoderRule.bundle().putBooleanArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private boolean[] generateArrayAndFill() {
        boolean[] array = new boolean[300];
        Arrays.fill(array, true);
        return array;
    }
}