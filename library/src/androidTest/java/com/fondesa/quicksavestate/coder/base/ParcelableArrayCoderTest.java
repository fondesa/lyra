package com.fondesa.quicksavestate.coder.base;

import android.os.Parcelable;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;
import com.fondesa.quicksavestate.testmodel.PersonParcelable;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
public class ParcelableArrayCoderTest {
    @Rule
    public final CoderRule<ParcelableArrayCoder> coderRule = new CoderRule<ParcelableArrayCoder>() {
        @Override
        protected ParcelableArrayCoder initCoder() {
            return new ParcelableArrayCoder();
        }
    };

    @Test
    public void testSerializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getParcelableArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        coderRule.bundle.putParcelableArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private Parcelable[] generateArrayAndFill() {
        PersonParcelable[] array = new PersonParcelable[300];
        PersonParcelable parcelable = new PersonParcelable("George", "White", 30);
        Arrays.fill(array, parcelable);
        return array;
    }
}