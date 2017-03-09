package com.fondesa.quicksavestate.coder.base;

import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.BundleCompat;

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
public class IBinderCoderTest {
    @Rule
    public final CoderRule<IBinderCoder> mCoderRule = new CoderRule<>(IBinderCoder.class);

    @Test
    public void testSerializeIBinder() {
        IBinder expectedValue = new Binder();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, BundleCompat.getBinder(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeIBinder() {
        IBinder expectedValue = new Binder();
        BundleCompat.putBinder(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}