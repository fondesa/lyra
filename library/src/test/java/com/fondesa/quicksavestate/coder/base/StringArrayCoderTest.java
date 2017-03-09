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
public class StringArrayCoderTest {
    @Rule
    public final CoderRule<StringArrayCoder> mCoderRule = new CoderRule<>(StringArrayCoder.class);

    @Test
    public void testSerializeStringArray() {
        String[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getStringArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeStringArray() {
        String[] expected = generateArrayAndFill();
        mCoderRule.bundle().putStringArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private String[] generateArrayAndFill() {
        String[] array = new String[300];
        Arrays.fill(array, "test");
        return array;
    }
}