package com.fondesa.quicksavestate.coder.base;

import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.BundleCompat;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class IBinderCoderTest {
    @Rule
    public final CoderRule<IBinderCoder> coderRule = new CoderRule<IBinderCoder>() {
        @Override
        protected IBinderCoder initCoder() {
            return new IBinderCoder();
        }
    };

    @Test
    public void testSerializeIBinder() {
        IBinder expectedValue = new Binder();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, BundleCompat.getBinder(coderRule.bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeIBinder() {
        IBinder expectedValue = new Binder();
        BundleCompat.putBinder(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}