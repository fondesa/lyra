package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;

import com.fondesa.quicksavestate.testmodel.PersonSerializable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@SuppressWarnings("ConstantConditions")
public class SerializableCoderTest {
    private static final String COMMON_KEY = "x";

    private SerializableCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new SerializableCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeSerializable() {
        Serializable expectedValue = generateSerializable();
        coder.serialize(bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, bundle.getSerializable(COMMON_KEY));
    }

    @Test
    public void testDeserializeSerializable() {
        Serializable expectedValue = generateSerializable();
        bundle.putSerializable(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coder.deserialize(bundle, COMMON_KEY));
    }
    
    private Serializable generateSerializable() {
        return new PersonSerializable("George", "White", 30);
    }
}