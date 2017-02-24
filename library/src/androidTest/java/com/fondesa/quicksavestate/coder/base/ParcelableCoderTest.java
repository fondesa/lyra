package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.os.Parcelable;

import com.fondesa.quicksavestate.testmodel.PersonParcelable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@SuppressWarnings("ConstantConditions")
public class ParcelableCoderTest {
    private static final String COMMON_KEY = "x";

    private ParcelableCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new ParcelableCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeParcelable() {
        Parcelable expectedValue = generateParcelable();
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getParcelable(COMMON_KEY));
    }

    @Test
    public void testDeserializeParcelable() {
        Parcelable expectedValue = generateParcelable();
        bundle.putParcelable(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
    
    private Parcelable generateParcelable() {
        PersonParcelable parcelable = new PersonParcelable();
        parcelable.name = "George";
        parcelable.surname = "White";
        parcelable.age = 30;
        return parcelable;
    }
}