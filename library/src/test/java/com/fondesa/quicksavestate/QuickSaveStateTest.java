package com.fondesa.quicksavestate;

import android.app.Application;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ApplicationTestUtil;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by antoniolig on 01/03/17.
 */
@RunWith(RobolectricTestRunner.class)
public class QuickSaveStateTest {

    @After
    public void tearDown() {
        // destroy the instance after each test
        QuickSaveState.destroy();
    }

    @Test
    public void testInit() {
        Application app = ApplicationTestUtil.newApplication(Application.class);
        QuickSaveState.with(app).build();
        assertNotNull(QuickSaveState.instance());
    }

    @Test(expected = NullPointerException.class)
    public void testCallInstanceThrowExceptionWithoutInit() {
        QuickSaveState.instance();
    }
}
