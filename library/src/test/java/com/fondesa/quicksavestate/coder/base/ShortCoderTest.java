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
public class ShortCoderTest {
    @Rule
    public final CoderRule<ShortCoder> coderRule = new CoderRule<>(ShortCoder.class);

    @Test
    public void testSerializeShortPrimitive() {
        short expectedValue = 9;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getShort(coderRule.randomKey));
    }

    @Test
    public void testSerializeShortObject() {
        Short expectedValue = 9;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, (Short) coderRule.bundle.getShort(coderRule.randomKey));
    }

    @Test
    public void testDeserializeShortPrimitive() {
        short expectedValue = 9;
        coderRule.bundle.putShort(coderRule.randomKey, expectedValue);
        assertEquals((Short) expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    @Test
    public void testDeserializeShortObject() {
        Short expectedValue = 9;
        coderRule.bundle.putShort(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}