package com.fondesa.quicksavestate.coder.base;

import android.os.Parcelable;

import com.fondesa.quicksavestate.coder.CoderRule;
import com.fondesa.quicksavestate.common.TestModels;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class ParcelableArrayCoderTest {
    @Rule
    public final CoderRule<ParcelableArrayCoder> mCoderRule = new CoderRule<>(ParcelableArrayCoder.class);

    @Test
    public void testSerializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getParcelableArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        mCoderRule.bundle().putParcelableArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private Parcelable[] generateArrayAndFill() {
        TestModels.ImplementedParcelable[] array = new TestModels.ImplementedParcelable[300];
        Arrays.fill(array, TestModels.ImplementedParcelable.getDefault());
        return array;
    }
}