package com.fondesa.quicksavestate.coder.base;


import android.util.SizeF;

import com.fondesa.quicksavestate.testhelper.rule.CoderRule;
import com.fondesa.quicksavestate.testhelper.util.TestUtil;

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
    public final CoderRule<SizeFCoder> coderRule = new CoderRule<>(SizeFCoder.class);

    @SuppressWarnings("NewApi")
    @Test
    public void testSerializeSizeF() {
        TestUtil.setApiVersion(21);
        SizeF expectedValue = new SizeF(9, 5);
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getSizeF(coderRule.randomKey));

    }

    @SuppressWarnings("NewApi")
    @Test
    public void testDeserializeSizeF() {
        TestUtil.setApiVersion(21);
        SizeF expectedValue = new SizeF(9, 5);
        coderRule.bundle.putSizeF(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}