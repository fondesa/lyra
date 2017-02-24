package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class StringCoderTest {
    private static final String COMMON_KEY = "x";
    @Rule
    public CoderRule<StringCoder> coderRule = new CoderRule<StringCoder>() {
        @Override
        protected StringCoder initCoder() {
            return new StringCoder();
        }
    };

    @Test
    public void testSerializeString() {
        String expectedValue = "test";
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getString(COMMON_KEY));
    }

    @Test
    public void testDeserializeString() {
        String expectedValue = "test";
        coderRule.bundle.putString(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}