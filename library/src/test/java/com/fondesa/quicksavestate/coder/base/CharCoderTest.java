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
    public final CoderRule<CharCoder> mCoderRule = new CoderRule<>(CharCoder.class);

    @Test
    public void testSerializeCharPrimitive() {
        char expectedValue = 'x';
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getChar(mCoderRule.randomKey()));
    }

    @Test
    public void testSerializeCharObject() {
        Character expectedValue = 'x';
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, ((Character) mCoderRule.bundle().getChar(mCoderRule.randomKey())));
    }

    @Test
    public void testDeserializeCharPrimitive() {
        char expectedValue = 'x';
        mCoderRule.bundle().putChar(mCoderRule.randomKey(), expectedValue);
        assertEquals((Character) expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeCharObject() {
        Character expectedValue = 'x';
        mCoderRule.bundle().putChar(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}