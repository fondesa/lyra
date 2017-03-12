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
//@Config(sdk = 24)
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class AutomaticSaveStateManagerTest {
    @Mock
    private AutomaticSaveStateManager.Listener mListener;
    private AutomaticSaveStateManager mSaveStateManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mSaveStateManager = new AutomaticSaveStateManager(true, true, mListener);
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
