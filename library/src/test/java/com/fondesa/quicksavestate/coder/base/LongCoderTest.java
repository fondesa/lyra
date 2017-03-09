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
public class LongCoderTest {
    @Rule
    public final CoderRule<LongCoder> mCoderRule = new CoderRule<>(LongCoder.class);

    @Test
    public void testSerializeLongPrimitive() {
        long expectedValue = 9L;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getLong(mCoderRule.randomKey()));
    }

    @Test
    public void testSerializeLongObject() {
        Long expectedValue = 9L;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, ((Long) mCoderRule.bundle().getLong(mCoderRule.randomKey())));
    }

    @Test
    public void testDeserializeLongPrimitive() {
        long expectedValue = 9L;
        mCoderRule.bundle().putLong(mCoderRule.randomKey(), expectedValue);
        assertEquals((Long) expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeLongObject() {
        Long expectedValue = 9L;
        mCoderRule.bundle().putLong(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}