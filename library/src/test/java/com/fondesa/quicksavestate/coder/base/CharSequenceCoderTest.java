package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.testhelper.rule.CoderRule;

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
    public final CoderRule<CharSequenceCoder> coderRule = new CoderRule<>(CharSequenceCoder.class);

    @Test
    public void testSerializeCharSequence() {
        CharSequence expectedValue = "test";
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getCharSequence(coderRule.randomKey));
    }

    @Test
    public void testDeserializeCharSequence() {
        CharSequence expectedValue = "test";
        coderRule.bundle.putCharSequence(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}