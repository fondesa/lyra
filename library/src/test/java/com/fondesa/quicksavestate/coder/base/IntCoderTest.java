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
public class IntCoderTest {
    @Rule
    public final CoderRule<IntCoder> mCoderRule = new CoderRule<>(IntCoder.class);

    @Test
    public void testSerializeIntPrimitive() {
        int expectedValue = 9;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getInt(mCoderRule.randomKey()));
    }

    @Test
    public void testSerializeIntObject() {
        Integer expectedValue = 9;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, (Integer) mCoderRule.bundle().getInt(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeIntPrimitive() {
        int expectedValue = 9;
        mCoderRule.bundle().putInt(mCoderRule.randomKey(), expectedValue);
        assertEquals((Integer) expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeIntObject() {
        Integer expectedValue = 9;
        mCoderRule.bundle().putInt(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}