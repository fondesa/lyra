package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@SuppressWarnings("ConstantConditions")
public class BooleanCoderTest {
    @Rule
    public CoderRule<BooleanCoder> coderRule = new CoderRule<BooleanCoder>() {
        @Override
        protected BooleanCoder initCoder() {
            return new BooleanCoder();
        }
    };

    @Test
    public void testSerializeBooleanPrimitive() {
        boolean expectedValue = true;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getBoolean(COMMON_KEY));
    }

    @Test
    public void testSerializeBooleanObject() {
        Boolean expectedValue = true;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, ((Boolean) coderRule.bundle.getBoolean(COMMON_KEY)));
    }

    @Test
    public void testDeserializeBooleanPrimitive() {
        boolean expectedValue = true;
        coderRule.bundle.putBoolean(COMMON_KEY, expectedValue);
        assertEquals((Boolean) expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeBooleanObject() {
        Boolean expectedValue = true;
        coderRule.bundle.putBoolean(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}