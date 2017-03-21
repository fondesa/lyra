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

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.fondesa.quicksavestate.common.TestModels;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * Created by antoniolig on 11/03/17.
 */
@RunWith(RobolectricTestRunner.class)
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class AutomaticSaveStateManagerTest {
    @Mock
    private AutomaticSaveStateManager.Listener mListener;
    private AutomaticSaveStateManager mSaveStateManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mSaveStateManager = new AutomaticSaveStateManager(mListener);
        RuntimeEnvironment.application.registerActivityLifecycleCallbacks(mSaveStateManager);
    }

    @After
    public void tearDown() {
        RuntimeEnvironment.application.unregisterActivityLifecycleCallbacks(mSaveStateManager);
    }

    @Test
    public void testActivityAutomaticSaveState() {
        Bundle stateBundle = new Bundle();
        TestModels.SaveStateActivity activity = Robolectric.buildActivity(TestModels.SaveStateActivity.class)
                .saveInstanceState(stateBundle)
                .get();

        verify(mListener, only()).onSaveState(activity, stateBundle);
    }

    @Test
    public void testActivityAutomaticRestoreState() {
        Bundle stateBundle = new Bundle();
        TestModels.SaveStateActivity activity = Robolectric.buildActivity(TestModels.SaveStateActivity.class)
                .create(stateBundle)
                .get();

        verify(mListener, only()).onRestoreState(activity, stateBundle);
    }
}
