package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class CharSequenceCoderTest {
    @Rule
    public final CoderRule<CharSequenceCoder> mCoderRule = new CoderRule<>(CharSequenceCoder.class);

    @Test
    public void testSerializeCharSequence() {
        CharSequence expectedValue = "test";
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getCharSequence(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeCharSequence() {
        CharSequence expectedValue = "test";
        mCoderRule.bundle().putCharSequence(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}