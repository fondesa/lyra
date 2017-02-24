package com.fondesa.quicksavestate.coder.utils;

import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Size;
import android.util.SizeF;

import com.fondesa.quicksavestate.exception.CoderNotFoundException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.Serializable;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by antoniolig on 24/02/17.
 */
public class StateCoderUtilsTest {
    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testBaseSupportedClassesCompatApi() throws CoderNotFoundException {
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

    @Test
    public void testBaseSupportedClassesApi21() throws CoderNotFoundException {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            exceptionRule.expect(NoClassDefFoundError.class);
        }
        assertCoderNotNull(Size.class);
        assertCoderNotNull(SizeF.class);
    }

    private static void assertCoderNotNull(@NonNull Class<?> cls) throws CoderNotFoundException {
        assertNotNull(StateCoderUtils.getBaseCoderForClass(cls));
    }
}
