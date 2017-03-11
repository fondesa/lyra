package com.fondesa.quicksavestate;

import android.app.Application;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ApplicationTestUtil;

/**
 * Created by antoniolig on 11/03/17.
 */
@RunWith(RobolectricTestRunner.class)
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class AutomaticSaveStateManagerTest {
    @BeforeClass
    public static void initInstance() {
        // init the instance before run all tests
        Application app = ApplicationTestUtil.newApplication(Application.class);
        QuickSaveState.with(app)
                .autoSaveActivities()
                .autoSaveSupportFragments()
                .build();
    }

    @AfterClass
    public static void destroyInstance() {
        // destroy the instance after all tests
        QuickSaveState.destroy();
    }
}
