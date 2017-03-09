package com.fondesa.quicksavestate.coder.base;


import android.util.SizeF;

import com.fondesa.quicksavestate.coder.CoderRule;
import com.fondesa.quicksavestate.common.TestUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class SizeFCoderTest {
    @Rule
    public final CoderRule<SizeFCoder> mCoderRule = new CoderRule<>(SizeFCoder.class);

    @SuppressWarnings("NewApi")
    @Test
    public void testSerializeSizeF() {
        TestUtils.setApiVersion(21);
        SizeF expectedValue = new SizeF(9, 5);
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getSizeF(mCoderRule.randomKey()));

    }

    @SuppressWarnings("NewApi")
    @Test
    public void testDeserializeSizeF() {
        TestUtils.setApiVersion(21);
        SizeF expectedValue = new SizeF(9, 5);
        mCoderRule.bundle().putSizeF(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}