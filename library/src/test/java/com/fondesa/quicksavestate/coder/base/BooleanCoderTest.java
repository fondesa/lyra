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
    public final CoderRule<BooleanCoder> mCoderRule = new CoderRule<>(BooleanCoder.class);

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSerializeBooleanPrimitive() {
        boolean expectedValue = true;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getBoolean(mCoderRule.randomKey()));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSerializeBooleanObject() {
        Boolean expectedValue = true;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, ((Boolean) mCoderRule.bundle().getBoolean(mCoderRule.randomKey())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDeserializeBooleanPrimitive() {
        boolean expectedValue = true;
        mCoderRule.bundle().putBoolean(mCoderRule.randomKey(), expectedValue);
        assertEquals((Boolean) expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDeserializeBooleanObject() {
        Boolean expectedValue = true;
        mCoderRule.bundle().putBoolean(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}