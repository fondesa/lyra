package com.fondesa.quicksavestate.coder.base;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.SizeF;

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
public class SizeFCoderTest {
    @Rule
    public final CoderRule<SizeFCoder> mCoderRule = new CoderRule<>(SizeFCoder.class);

    @Test
    public void testSerializeSizeF() {
        SizeF expectedValue = new SizeF(9, 5);
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getSizeF(mCoderRule.randomKey()));

    }

    @Test
    public void testDeserializeSizeF() {
        SizeF expectedValue = new SizeF(9, 5);
        mCoderRule.bundle().putSizeF(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}