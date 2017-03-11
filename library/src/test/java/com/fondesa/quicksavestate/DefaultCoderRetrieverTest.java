package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.coder.utils.StateCoderUtils;
import com.fondesa.quicksavestate.exception.CoderNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by antoniolig on 09/03/17.
 */
@RunWith(RobolectricTestRunner.class)
public class DefaultCoderRetrieverTest {

    private DefaultCoderRetriever mRetriever;

    @Before
    public void initRetriever() {
        mRetriever = new DefaultCoderRetriever();
    }

    @After
    public void releaseRetriever() {
        mRetriever = null;
    }

    @Test
    public void testRetrieveBasicCoder() throws CoderNotFoundException {
        Class fieldClass = String.class;

        SaveState mockedSaveState = mock(SaveState.class);
        when(mockedSaveState.value()).thenAnswer(new Answer<Class<? extends StateCoder>>() {
            @Override
            public Class<? extends StateCoder> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return StateCoder.class;
            }
        });

        StateCoder retrieverCoder = mRetriever.getCoder(mockedSaveState, fieldClass);
        assertNotNull(retrieverCoder);

        StateCoder basicCoder = StateCoderUtils.getBasicCoderForClass(fieldClass);
        assertEquals(retrieverCoder.getClass(), basicCoder.getClass());
    }

    @Test
    public void testRetrieveBasicCachedCoder() throws CoderNotFoundException {
        Class fieldClass = String.class;

        SaveState mockedSaveState = mock(SaveState.class);
        when(mockedSaveState.value()).thenAnswer(new Answer<Class<? extends StateCoder>>() {
            @Override
            public Class<? extends StateCoder> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return StateCoder.class;
            }
        });

        StateCoder retrieverCoder = mRetriever.getCoder(mockedSaveState, fieldClass);
        assertNotNull(retrieverCoder);

        StateCoder basicCoder = StateCoderUtils.getBasicCoderForClass(fieldClass);
        assertEquals(retrieverCoder.getClass(), basicCoder.getClass());

        retrieverCoder = mRetriever.getCoder(mockedSaveState, fieldClass);
        assertNotNull(retrieverCoder);
        assertEquals(retrieverCoder.getClass(), basicCoder.getClass());
    }

    @Test
    public void testRetrieveCustomCoder() {
        Class fieldClass = String.class;
        SaveState mockedSaveState = mock(SaveState.class);
        when(mockedSaveState.value()).thenAnswer(new Answer<Class<? extends StateCoder>>() {
            @Override
            public Class<? extends StateCoder> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return CustomStateCoder.class;
            }
        });
        StateCoder retrieverCoder = mRetriever.getCoder(mockedSaveState, fieldClass);
        assertNotNull(retrieverCoder);
        assertEquals(retrieverCoder.getClass(), CustomStateCoder.class);
    }

    public static class CustomStateCoder implements StateCoder<String> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull String fieldValue) { /* empty */ }

        @Override
        public String deserialize(@NonNull Bundle state, @NonNull String fieldName) {
            return null;
        }
    }
}
