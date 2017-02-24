package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class DoubleCoderTest {
    @Rule
    public final CoderRule<DoubleCoder> coderRule = new CoderRule<DoubleCoder>() {
        @Override
        protected DoubleCoder initCoder() {
            return new DoubleCoder();
        }
    };

    @Test
    public void testSerializeDoublePrimitive() {
        double expectedValue = 9.0;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getDouble(COMMON_KEY), 0);
    }

    @Test
    public void testSerializeDoubleObject() {
        Double expectedValue = 9.0;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getDouble(COMMON_KEY));
    }

    @Test
    public void testDeserializeDoublePrimitive() {
        double expectedValue = 9.0;
        coderRule.bundle.putDouble(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY), 0);
    }

    @Test
    public void testDeserializeDoubleObject() {
        Double expectedValue = 9.0;
        coderRule.bundle.putDouble(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}