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
public class FloatCoderTest {
    @Rule
    public final CoderRule<FloatCoder> coderRule = new CoderRule<>(FloatCoder.class);

    @Test
    public void testSerializeFloatPrimitive() {
        float expectedValue = 9.0f;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getFloat(coderRule.randomKey), 0);
    }

    @Test
    public void testSerializeFloatObject() {
        Float expectedValue = 9.0f;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getFloat(coderRule.randomKey));
    }

    @Test
    public void testDeserializeFloatPrimitive() {
        float expectedValue = 9.0f;
        coderRule.bundle.putFloat(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey), 0);
    }

    @Test
    public void testDeserializeFloatObject() {
        Float expectedValue = 9.0f;
        coderRule.bundle.putFloat(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}