/*
 * Copyright (c) 2017 Fondesa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.coder.utils.StateCoderUtils;
import com.fondesa.quicksavestate.exception.CoderNotFoundException;

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
 * Unit test class for {@link DefaultCoderRetriever}.
 */
@RunWith(RobolectricTestRunner.class)
public class DefaultCoderRetrieverTest {

    private DefaultCoderRetriever mRetriever;

    @Before
    public void setUp() {
        mRetriever = new DefaultCoderRetriever();
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
    public void testRetrieveCustomCoder() throws CoderNotFoundException {
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
