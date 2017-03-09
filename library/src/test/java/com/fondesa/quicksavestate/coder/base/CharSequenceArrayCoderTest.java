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
public class CharSequenceArrayCoderTest {
    @Rule
    public final CoderRule<CharSequenceArrayCoder> mCoderRule = new CoderRule<>(CharSequenceArrayCoder.class);

    @Test
    public void testSerializeCharSequenceArray() {
        CharSequence[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getCharSequenceArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeCharSequenceArray() {
        CharSequence[] expected = generateArrayAndFill();
        mCoderRule.bundle().putCharSequenceArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private CharSequence[] generateArrayAndFill() {
        CharSequence[] array = new CharSequence[300];
        Arrays.fill(array, "test");
        return array;
    }
}