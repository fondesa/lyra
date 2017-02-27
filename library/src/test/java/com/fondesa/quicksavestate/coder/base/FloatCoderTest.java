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
public class FloatCoderTest {
    @Rule
    public final CoderRule<FloatCoder> coderRule = new CoderRule<>(FloatCoder.class);

    @Test
    public void testSerializeFloatPrimitive() {
        float expectedValue = 9.0f;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getFloat(COMMON_KEY), 0);
    }

    @Test
    public void testSerializeFloatObject() {
        Float expectedValue = 9.0f;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getFloat(COMMON_KEY));
    }

    @Test
    public void testDeserializeFloatPrimitive() {
        float expectedValue = 9.0f;
        coderRule.bundle.putFloat(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY), 0);
    }

    @Test
    public void testDeserializeFloatObject() {
        Float expectedValue = 9.0f;
        coderRule.bundle.putFloat(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}