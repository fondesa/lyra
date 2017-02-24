package com.fondesa.quicksavestate.coder.base;


import android.os.Build;
import android.util.SizeF;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class SizeFCoderTest {
    @Rule
    public final CoderRule<SizeFCoder> coderRule = new CoderRule<SizeFCoder>() {
        @Override
        protected SizeFCoder initCoder() {
            return new SizeFCoder();
        }
    };

    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @SuppressWarnings("NewApi")
    @Test
    public void testSerializeSizeF() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            exceptionRule.expect(NoClassDefFoundError.class);
        }
        SizeF expectedValue = new SizeF(9, 5);
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getSizeF(COMMON_KEY));

    }

    @SuppressWarnings("NewApi")
    @Test
    public void testDeserializeSizeF() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            exceptionRule.expect(NoClassDefFoundError.class);
        }
        SizeF expectedValue = new SizeF(9, 5);
        coderRule.bundle.putSizeF(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}