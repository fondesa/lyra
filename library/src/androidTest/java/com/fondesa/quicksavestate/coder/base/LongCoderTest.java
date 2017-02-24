package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class LongCoderTest {
    @Rule
    public final CoderRule<LongCoder> coderRule = new CoderRule<LongCoder>() {
        @Override
        protected LongCoder initCoder() {
            return new LongCoder();
        }
    };

    @Test
    public void testSerializeLongPrimitive() {
        long expectedValue = 9L;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getLong(COMMON_KEY));
    }

    @Test
    public void testSerializeLongObject() {
        Long expectedValue = 9L;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, ((Long) coderRule.bundle.getLong(COMMON_KEY)));
    }

    @Test
    public void testDeserializeLongPrimitive() {
        long expectedValue = 9L;
        coderRule.bundle.putLong(COMMON_KEY, expectedValue);
        assertEquals((Long) expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeLongObject() {
        Long expectedValue = 9L;
        coderRule.bundle.putLong(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}