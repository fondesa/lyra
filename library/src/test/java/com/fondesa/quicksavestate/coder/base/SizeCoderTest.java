package com.fondesa.quicksavestate.coder.base;


import android.util.Size;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;
import com.fondesa.quicksavestate.testhelper.util.TestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@SuppressWarnings("NewApi")
@RunWith(RobolectricTestRunner.class)
public class SizeCoderTest {
    @Rule
    public final CoderRule<SizeCoder> coderRule = new CoderRule<>(SizeCoder.class);

    @SuppressWarnings("NewApi")
    @Test
    public void testSerializeSize() {
        TestUtil.setApiVersion(21);
        Size expectedValue = new Size(9, 5);
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getSize(COMMON_KEY));

    }

    @Test
    public void testDeserializeSize() {
        TestUtil.setApiVersion(21);
        Size expectedValue = new Size(9, 5);
        coderRule.bundle.putSize(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}