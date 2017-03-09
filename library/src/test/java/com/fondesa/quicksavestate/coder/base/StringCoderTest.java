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
public class StringCoderTest {
    @Rule
    public final CoderRule<StringCoder> mCoderRule = new CoderRule<>(StringCoder.class);

    @Test
    public void testSerializeString() {
        String expectedValue = "test";
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getString(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeString() {
        String expectedValue = "test";
        mCoderRule.bundle().putString(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}