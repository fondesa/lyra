package com.fondesa.quicksavestate.coder.base;

import android.os.Parcelable;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;
import com.fondesa.quicksavestate.testmodel.PersonParcelable;

import org.junit.Rule;
import org.junit.Test;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class ParcelableCoderTest {
    @Rule
    public CoderRule<ParcelableCoder> coderRule = new CoderRule<ParcelableCoder>() {
        @Override
        protected ParcelableCoder initCoder() {
            return new ParcelableCoder();
        }
    };

    @Test
    public void testSerializeParcelable() {
        Parcelable expectedValue = generateParcelable();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getParcelable(COMMON_KEY));
    }

    @Test
    public void testDeserializeParcelable() {
        Parcelable expectedValue = generateParcelable();
        coderRule.bundle.putParcelable(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
    
    private Parcelable generateParcelable() {
        return new PersonParcelable("George", "White", 30);
    }
}