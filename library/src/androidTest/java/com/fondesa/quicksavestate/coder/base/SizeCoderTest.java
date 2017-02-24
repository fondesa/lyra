package com.fondesa.quicksavestate.coder.base;


import android.os.Build;
import android.util.Size;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class SizeCoderTest {
    @Rule
    public final CoderRule<SizeCoder> coderRule = new CoderRule<SizeCoder>() {
        @Override
        protected SizeCoder initCoder() {
            return new SizeCoder();
        }
    };

    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @SuppressWarnings("NewApi")
    @Test
    public void testSerializeSize() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            exceptionRule.expect(NoClassDefFoundError.class);
        }
        Size expectedValue = new Size(9, 5);
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getSize(COMMON_KEY));

    }

    @SuppressWarnings("NewApi")
    @Test
    public void testDeserializeSize() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            exceptionRule.expect(NoClassDefFoundError.class);
        }
        Size expectedValue = new Size(9, 5);
        coderRule.bundle.putSize(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}