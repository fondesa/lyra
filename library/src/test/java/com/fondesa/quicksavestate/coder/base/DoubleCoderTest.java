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
public class DoubleCoderTest {
    @Rule
    public final CoderRule<DoubleCoder> mCoderRule = new CoderRule<>(DoubleCoder.class);

    @Test
    public void testSerializeDoublePrimitive() {
        double expectedValue = 9.0;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getDouble(mCoderRule.randomKey()), 0);
    }

    @Test
    public void testSerializeDoubleObject() {
        Double expectedValue = 9.0;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getDouble(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeDoublePrimitive() {
        double expectedValue = 9.0;
        mCoderRule.bundle().putDouble(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()), 0);
    }

    @Test
    public void testDeserializeDoubleObject() {
        Double expectedValue = 9.0;
        mCoderRule.bundle().putDouble(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}