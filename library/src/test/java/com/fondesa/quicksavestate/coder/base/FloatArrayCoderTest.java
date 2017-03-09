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
public class FloatArrayCoderTest {
    @Rule
    public final CoderRule<FloatArrayCoder> mCoderRule = new CoderRule<>(FloatArrayCoder.class);

    @Test
    public void testSerializeFloatArray() {
        float[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getFloatArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeFloatArray() {
        float[] expected = generateArrayAndFill();
        mCoderRule.bundle().putFloatArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private float[] generateArrayAndFill() {
        float[] array = new float[300];
        Arrays.fill(array, 9.0f);
        return array;
    }
}