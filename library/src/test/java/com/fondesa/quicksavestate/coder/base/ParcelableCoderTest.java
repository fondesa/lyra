package com.fondesa.quicksavestate.coder.base;

import android.os.Parcelable;

import com.fondesa.quicksavestate.coder.CoderRule;
import com.fondesa.quicksavestate.common.TestModels;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class ParcelableCoderTest {
    @Rule
    public final CoderRule<ParcelableCoder> mCoderRule = new CoderRule<>(ParcelableCoder.class);

    @Test
    public void testSerializeParcelable() {
        Parcelable expectedValue = TestModels.ImplementedParcelable.getDefault();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getParcelable(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeParcelable() {
        Parcelable expectedValue = TestModels.ImplementedParcelable.getDefault();
        mCoderRule.bundle().putParcelable(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}