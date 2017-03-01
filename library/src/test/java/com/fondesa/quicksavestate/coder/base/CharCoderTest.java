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
public class CharCoderTest {
    @Rule
    public final CoderRule<CharCoder> coderRule = new CoderRule<>(CharCoder.class);

    @Test
    public void testSerializeCharPrimitive() {
        char expectedValue = 'x';
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getChar(coderRule.randomKey));
    }

    @Test
    public void testSerializeCharObject() {
        Character expectedValue = 'x';
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, ((Character) coderRule.bundle.getChar(coderRule.randomKey)));
    }

    @Test
    public void testDeserializeCharPrimitive() {
        char expectedValue = 'x';
        coderRule.bundle.putChar(coderRule.randomKey, expectedValue);
        assertEquals((Character) expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    @Test
    public void testDeserializeCharObject() {
        Character expectedValue = 'x';
        coderRule.bundle.putChar(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}