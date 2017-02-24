package com.fondesa.quicksavestate.coder.base;

import android.os.Bundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class BooleanCoderTest {

    private BooleanCoder coder;

    @Mock
    private Bundle bundle;

    @Captor
    private ArgumentCaptor<Bundle> bundleCaptor;

    @Before
    public void initCoder() {
        coder = new BooleanCoder();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializePrimitive() {
        final String key = "x";

        coder.serialize(bundle, key, true);
        verify(bundle).putBoolean(key, true);
    }

    @Test
    public void testSerialize() {
        final String key = "x";
        boolean expectedValue = true;

        BooleanCoder coder = new BooleanCoder();
        Bundle bundle = new Bundle();
        coder.serialize(bundle, key, expectedValue);
        assertEquals(expectedValue, bundle.getBoolean(key));
    }

    @Test
    public void testDeserialize() {
        final String key = "x";

        coder.deserialize(bundle, key);
        verify(bundle).get(key);
    }

    private static class Person {
        boolean prova;

        public void setProva(Boolean prova) {
            this.prova = prova;
        }
    }
}
