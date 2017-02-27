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
public class StringCoderTest {
    @Rule
    public final CoderRule<StringCoder> coderRule = new CoderRule<>(StringCoder.class);

    @Test
    public void testSerializeString() {
        String expectedValue = "test";
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getString(coderRule.randomKey));
    }

    @Test
    public void testDeserializeString() {
        String expectedValue = "test";
        coderRule.bundle.putString(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}