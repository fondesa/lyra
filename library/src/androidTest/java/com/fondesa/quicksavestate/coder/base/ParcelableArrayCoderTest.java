package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;
import android.os.Parcelable;

import com.fondesa.quicksavestate.testmodel.PersonParcelable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
public class ParcelableArrayCoderTest {
    private static final String COMMON_KEY = "x";

    private ParcelableArrayCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new ParcelableArrayCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        coder.serialize(bundle, COMMON_KEY, expected);
        assertEquals(expected, bundle.getParcelableArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        bundle.putParcelableArray(COMMON_KEY, expected);
        assertEquals(expected, coder.deserialize(bundle, COMMON_KEY));
    }

    private Parcelable[] generateArrayAndFill() {
        PersonParcelable[] array = new PersonParcelable[300];
        PersonParcelable parcelable = new PersonParcelable("George", "White", 30);
        Arrays.fill(array, parcelable);
        return array;
    }
}