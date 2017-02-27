package com.fondesa.quicksavestate.coder.base;

import android.os.Parcelable;

import com.fondesa.quicksavestate.testhelper.model.ImplementedParcelable;
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
public class ParcelableCoderTest {
    @Rule
    public final CoderRule<ParcelableCoder> coderRule = new CoderRule<>(ParcelableCoder.class);

    @Test
    public void testSerializeParcelable() {
        Parcelable expectedValue = ImplementedParcelable.getDefault();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getParcelable(coderRule.randomKey));
    }

    @Test
    public void testDeserializeParcelable() {
        Parcelable expectedValue = ImplementedParcelable.getDefault();
        coderRule.bundle.putParcelable(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}