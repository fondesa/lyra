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
public class IntCoderTest {
    @Rule
    public final CoderRule<IntCoder> coderRule = new CoderRule<>(IntCoder.class);

    @Test
    public void testSerializeIntPrimitive() {
        int expectedValue = 9;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getInt(coderRule.randomKey));
    }

    @Test
    public void testSerializeIntObject() {
        Integer expectedValue = 9;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, (Integer) coderRule.bundle.getInt(coderRule.randomKey));
    }

    @Test
    public void testDeserializeIntPrimitive() {
        int expectedValue = 9;
        coderRule.bundle.putInt(coderRule.randomKey, expectedValue);
        assertEquals((Integer) expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    @Test
    public void testDeserializeIntObject() {
        Integer expectedValue = 9;
        coderRule.bundle.putInt(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}