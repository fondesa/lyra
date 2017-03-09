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
public class CharArrayCoderTest {
    @Rule
    public final CoderRule<CharArrayCoder> mCoderRule = new CoderRule<>(CharArrayCoder.class);

    @Test
    public void testSerializeCharArray() {
        char[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getCharArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeCharArray() {
        char[] expected = generateArrayAndFill();
        mCoderRule.bundle().putCharArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private char[] generateArrayAndFill() {
        char[] array = new char[300];
        Arrays.fill(array, 'x');
        return array;
    }
}