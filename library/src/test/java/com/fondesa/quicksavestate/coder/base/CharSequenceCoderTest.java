package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class CharSequenceCoderTest {
    @Rule
    public final CoderRule<CharSequenceCoder> coderRule = new CoderRule<>(CharSequenceCoder.class);

    @Test
    public void testSerializeCharSequence() {
        CharSequence expectedValue = "test";
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getCharSequence(COMMON_KEY));
    }

    @Test
    public void testDeserializeCharSequence() {
        CharSequence expectedValue = "test";
        coderRule.bundle.putCharSequence(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}