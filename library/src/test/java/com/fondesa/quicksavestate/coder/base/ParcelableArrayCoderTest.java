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
    public final CoderRule<ParcelableArrayCoder> coderRule = new CoderRule<>(ParcelableArrayCoder.class);

    @Test
    public void testSerializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expected);
        assertEquals(expected, coderRule.bundle.getParcelableArray(coderRule.randomKey));
    }

    @Test
    public void testDeserializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        coderRule.bundle.putParcelableArray(coderRule.randomKey, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    private Parcelable[] generateArrayAndFill() {
        TestModels.ImplementedParcelable[] array = new TestModels.ImplementedParcelable[300];
        Arrays.fill(array, TestModels.ImplementedParcelable.getDefault());
        return array;
    }
}