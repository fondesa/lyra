package com.fondesa.quicksavestate.coder.utils;

import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Size;
import android.util.SizeF;

import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.coder.base.StringCoder;
import com.fondesa.quicksavestate.exception.CoderNotFoundException;
import com.fondesa.quicksavestate.testmodel.ImplementedCharSequence;
import com.fondesa.quicksavestate.testmodel.ImplementedParcelable;
import com.fondesa.quicksavestate.testmodel.ImplementedSerializable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by antoniolig on 24/02/17.
 */
public class StateCoderUtilsTest {
    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

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

    @Test
    public void testBasicSupportedTypesApi21() throws CoderNotFoundException {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            exceptionRule.expect(NoClassDefFoundError.class);
        }
        assertCoderNotNull(Size.class);
        assertCoderNotNull(SizeF.class);
    }

    @Test
    public void testInheritedSupportedTypesCompatApi() throws CoderNotFoundException {
        assertCoderNotNull(ArrayList.class);
        assertCoderNotNull(Binder.class);
        assertCoderNotNull(ImplementedCharSequence.class);
        assertCoderNotNull(ImplementedCharSequence[].class);
        assertCoderNotNull(ImplementedParcelable.class);
        assertCoderNotNull(ImplementedParcelable[].class);
        assertCoderNotNull(ImplementedSerializable.class);
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
