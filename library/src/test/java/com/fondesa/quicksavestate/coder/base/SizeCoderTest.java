package com.fondesa.quicksavestate.coder.base;


import android.util.Size;

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
@SuppressWarnings("NewApi")
@RunWith(RobolectricTestRunner.class)
public class SizeCoderTest {
    @Rule
    public final CoderRule<SizeCoder> coderRule = new CoderRule<>(SizeCoder.class);

    @SuppressWarnings("NewApi")
    @Test
    public void testSerializeSize() {
        TestUtils.setApiVersion(21);
        Size expectedValue = new Size(9, 5);
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getSize(coderRule.randomKey));

    }

    @Test
    public void testDeserializeSize() {
        TestUtils.setApiVersion(21);
        Size expectedValue = new Size(9, 5);
        coderRule.bundle.putSize(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}