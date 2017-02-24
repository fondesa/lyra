package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class ShortCoderTest {
    @Rule
    public final CoderRule<ShortCoder> coderRule = new CoderRule<ShortCoder>() {
        @Override
        protected ShortCoder initCoder() {
            return new ShortCoder();
        }
    };

    @Test
    public void testSerializeShortPrimitive() {
        short expectedValue = 9;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getShort(COMMON_KEY));
    }

    @Test
    public void testSerializeShortObject() {
        Short expectedValue = 9;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, (Short) coderRule.bundle.getShort(COMMON_KEY));
    }

    @Test
    public void testDeserializeShortPrimitive() {
        short expectedValue = 9;
        coderRule.bundle.putShort(COMMON_KEY, expectedValue);
        assertEquals((Short) expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeShortObject() {
        Short expectedValue = 9;
        coderRule.bundle.putShort(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}