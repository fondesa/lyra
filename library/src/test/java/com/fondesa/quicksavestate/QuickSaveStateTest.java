package com.fondesa.quicksavestate;

import android.app.Application;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ApplicationTestUtil;

import static junit.framework.Assert.assertTrue;

/**
 * Created by antoniolig on 01/03/17.
 */
@RunWith(RobolectricTestRunner.class)
public class QuickSaveStateTest {

    @BeforeClass
    public static void initInstance() {
        // init the instance before run all tests
        Application app = ApplicationTestUtil.newApplication(Application.class);
        QuickSaveState.with(app).build();
    }

    @AfterClass
    public static void destroyInstance() {
        // destroy the instance after all tests
        QuickSaveState.destroy();
    }

    @Test
    public void testInit() {
        assertTrue(QuickSaveState.isInitialized());
    }


}
