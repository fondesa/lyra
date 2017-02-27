package com.fondesa.quicksavestate.coder.base;

import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.BundleCompat;

import com.fondesa.quicksavestate.testhelper.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class IBinderCoderTest {
    @Rule
    public final CoderRule<IBinderCoder> coderRule = new CoderRule<>(IBinderCoder.class);

    @Test
    public void testSerializeIBinder() {
        IBinder expectedValue = new Binder();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, BundleCompat.getBinder(coderRule.bundle, coderRule.randomKey));
    }

    @Test
    public void testDeserializeIBinder() {
        IBinder expectedValue = new Binder();
        BundleCompat.putBinder(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}