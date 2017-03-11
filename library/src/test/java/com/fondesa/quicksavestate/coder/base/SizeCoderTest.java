package com.fondesa.quicksavestate.coder.base;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Size;

import com.fondesa.quicksavestate.coder.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(minSdk = Build.VERSION_CODES.LOLLIPOP)
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SizeCoderTest {
    @Rule
    public final CoderRule<SizeCoder> mCoderRule = new CoderRule<>(SizeCoder.class);

    @SuppressWarnings("NewApi")
    @Test
    public void testSerializeSize() {
        Size expectedValue = new Size(9, 5);
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getSize(mCoderRule.randomKey()));

    }

    @Test
    public void testDeserializeSize() {
        Size expectedValue = new Size(9, 5);
        mCoderRule.bundle().putSize(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}