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
    public final CoderRule<FloatCoder> mCoderRule = new CoderRule<>(FloatCoder.class);

    @Test
    public void testSerializeFloatPrimitive() {
        float expectedValue = 9.0f;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getFloat(mCoderRule.randomKey()), 0);
    }

    @Test
    public void testSerializeFloatObject() {
        Float expectedValue = 9.0f;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getFloat(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeFloatPrimitive() {
        float expectedValue = 9.0f;
        mCoderRule.bundle().putFloat(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()), 0);
    }

    @Test
    public void testDeserializeFloatObject() {
        Float expectedValue = 9.0f;
        mCoderRule.bundle().putFloat(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}