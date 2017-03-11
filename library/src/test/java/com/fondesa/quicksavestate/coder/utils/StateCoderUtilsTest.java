package com.fondesa.quicksavestate.coder.utils;

import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Size;
import android.util.SizeF;

import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.coder.base.StringCoder;
import com.fondesa.quicksavestate.common.TestModels;
import com.fondesa.quicksavestate.exception.CoderNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by antoniolig on 24/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class StateCoderUtilsTest {
    @Test
    public void testBasicSupportedTypesCompatApi() throws CoderNotFoundException {
        assertCoderNotNull(boolean.class);
        assertCoderNotNull(Boolean.class);
        assertCoderNotNull(boolean[].class);
        assertCoderNotNull(byte.class);
        assertCoderNotNull(Byte.class);
        assertCoderNotNull(byte[].class);
        assertCoderNotNull(char.class);
        assertCoderNotNull(Character.class);
        assertCoderNotNull(char[].class);
        assertCoderNotNull(CharSequence.class);
        assertCoderNotNull(CharSequence[].class);
        assertCoderNotNull(double.class);
        assertCoderNotNull(Double.class);
        assertCoderNotNull(double[].class);
        assertCoderNotNull(float.class);
        assertCoderNotNull(Float.class);
        assertCoderNotNull(float[].class);
        assertCoderNotNull(IBinder.class);
        assertCoderNotNull(int.class);
        assertCoderNotNull(Integer.class);
        assertCoderNotNull(int[].class);
        assertCoderNotNull(long.class);
        assertCoderNotNull(Long.class);
        assertCoderNotNull(long[].class);
        assertCoderNotNull(Parcelable.class);
        assertCoderNotNull(Parcelable[].class);
        assertCoderNotNull(Serializable.class);
        assertCoderNotNull(short.class);
        assertCoderNotNull(Short.class);
        assertCoderNotNull(short[].class);
        assertCoderNotNull(String.class);
        assertCoderNotNull(String[].class);
    }

    @Config(minSdk = Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Test
    public void testCoderApi21NotNullAbove21() throws CoderNotFoundException {
        assertCoderNotNull(Size.class);
        assertCoderNotNull(SizeF.class);
    }

    @Config(maxSdk = Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Test(expected = CoderNotFoundException.class)
    public void testCoderApi21ThrowsBelowApi21() throws CoderNotFoundException {
        assertCoderNotNull(Size.class);
        assertCoderNotNull(SizeF.class);
    }

    @Test
    public void testInheritedSupportedTypesCompatApi() throws CoderNotFoundException {
        assertCoderNotNull(ArrayList.class);
        assertCoderNotNull(Binder.class);
        assertCoderNotNull(TestModels.ImplementedCharSequence.class);
        assertCoderNotNull(TestModels.ImplementedCharSequence[].class);
        assertCoderNotNull(TestModels.ImplementedParcelable.class);
        assertCoderNotNull(TestModels.ImplementedParcelable[].class);
        assertCoderNotNull(TestModels.ImplementedSerializable.class);
        assertCoderNotNull(LinkedList.class);
    }

    @Test
    public void testRightCoderForInheritedBasicSupportedType() throws CoderNotFoundException {
        StateCoder coder = StateCoderUtils.getBasicCoderForClass(String.class);
        assertEquals(StringCoder.class, coder.getClass());
    }

    private static void assertCoderNotNull(@NonNull Class<?> cls) throws CoderNotFoundException {
        assertNotNull(StateCoderUtils.getBasicCoderForClass(cls));
    }
}
