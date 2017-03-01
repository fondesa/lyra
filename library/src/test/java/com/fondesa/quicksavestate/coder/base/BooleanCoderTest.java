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
public class BooleanCoderTest {
    @Rule
    public final CoderRule<BooleanCoder> coderRule = new CoderRule<>(BooleanCoder.class);

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSerializeBooleanPrimitive() {
        boolean expectedValue = true;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getBoolean(coderRule.randomKey));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSerializeBooleanObject() {
        Boolean expectedValue = true;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, ((Boolean) coderRule.bundle.getBoolean(coderRule.randomKey)));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDeserializeBooleanPrimitive() {
        boolean expectedValue = true;
        coderRule.bundle.putBoolean(coderRule.randomKey, expectedValue);
        assertEquals((Boolean) expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDeserializeBooleanObject() {
        Boolean expectedValue = true;
        coderRule.bundle.putBoolean(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}