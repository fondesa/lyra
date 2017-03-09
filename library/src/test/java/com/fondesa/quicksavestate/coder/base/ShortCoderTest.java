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
public class ShortCoderTest {
    @Rule
    public final CoderRule<ShortCoder> mCoderRule = new CoderRule<>(ShortCoder.class);

    @Test
    public void testSerializeShortPrimitive() {
        short expectedValue = 9;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getShort(mCoderRule.randomKey()));
    }

    @Test
    public void testSerializeShortObject() {
        Short expectedValue = 9;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, (Short) mCoderRule.bundle().getShort(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeShortPrimitive() {
        short expectedValue = 9;
        mCoderRule.bundle().putShort(mCoderRule.randomKey(), expectedValue);
        assertEquals((Short) expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeShortObject() {
        Short expectedValue = 9;
        mCoderRule.bundle().putShort(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}