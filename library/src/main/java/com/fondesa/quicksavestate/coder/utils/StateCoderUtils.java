package com.fondesa.quicksavestate.coder.utils;

import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Size;
import android.util.SizeF;

import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.coder.base.BooleanArrayCoder;
import com.fondesa.quicksavestate.coder.base.BooleanCoder;
import com.fondesa.quicksavestate.coder.base.ByteArrayCoder;
import com.fondesa.quicksavestate.coder.base.ByteCoder;
import com.fondesa.quicksavestate.coder.base.CharArrayCoder;
import com.fondesa.quicksavestate.coder.base.CharCoder;
import com.fondesa.quicksavestate.coder.base.CharSequenceArrayCoder;
import com.fondesa.quicksavestate.coder.base.CharSequenceCoder;
import com.fondesa.quicksavestate.coder.base.DoubleArrayCoder;
import com.fondesa.quicksavestate.coder.base.DoubleCoder;
import com.fondesa.quicksavestate.coder.base.FloatArrayCoder;
import com.fondesa.quicksavestate.coder.base.FloatCoder;
import com.fondesa.quicksavestate.coder.base.IBinderCoder;
import com.fondesa.quicksavestate.coder.base.IntArrayCoder;
import com.fondesa.quicksavestate.coder.base.IntCoder;
import com.fondesa.quicksavestate.coder.base.LongArrayCoder;
import com.fondesa.quicksavestate.coder.base.LongCoder;
import com.fondesa.quicksavestate.coder.base.ParcelableArrayCoder;
import com.fondesa.quicksavestate.coder.base.ParcelableCoder;
import com.fondesa.quicksavestate.coder.base.SerializableCoder;
import com.fondesa.quicksavestate.coder.base.ShortArrayCoder;
import com.fondesa.quicksavestate.coder.base.ShortCoder;
import com.fondesa.quicksavestate.coder.base.SizeCoder;
import com.fondesa.quicksavestate.coder.base.SizeFCoder;
import com.fondesa.quicksavestate.coder.base.StringArrayCoder;
import com.fondesa.quicksavestate.coder.base.StringCoder;

import java.io.Serializable;

/**
 * Created by antoniolig on 22/02/17.
 */

public class StateCoderUtils {

    private StateCoderUtils() {
        // empty private constructor to avoid instantiation
    }

    public static StateCoder getBaseCoderForClass(@NonNull Class<?> cls) {
        if (boolean.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls))
            return new BooleanCoder();

        if (boolean[].class.isAssignableFrom(cls))
            return new BooleanArrayCoder();

        if (byte.class.isAssignableFrom(cls) || Byte.class.isAssignableFrom(cls))
            return new ByteCoder();

        if (byte[].class.isAssignableFrom(cls))
            return new ByteArrayCoder();

        if (char.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls))
            return new CharCoder();

        if (char[].class.isAssignableFrom(cls))
            return new CharArrayCoder();

        if (CharSequence.class.isAssignableFrom(cls))
            return new CharSequenceCoder();

        if (CharSequence[].class.isAssignableFrom(cls))
            return new CharSequenceArrayCoder();

        if (double.class.isAssignableFrom(cls) || Double.class.isAssignableFrom(cls))
            return new DoubleCoder();

        if (double[].class.isAssignableFrom(cls))
            return new DoubleArrayCoder();

        if (float.class.isAssignableFrom(cls) || Float.class.isAssignableFrom(cls))
            return new FloatCoder();

        if (float[].class.isAssignableFrom(cls))
            return new FloatArrayCoder();

        if (int.class.isAssignableFrom(cls) || Integer.class.isAssignableFrom(cls))
            return new IntCoder();

        if (int[].class.isAssignableFrom(cls) || Integer[].class.isAssignableFrom(cls))
            return new IntArrayCoder();

        if (long.class.isAssignableFrom(cls) || Long.class.isAssignableFrom(cls))
            return new LongCoder();

        if (long[].class.isAssignableFrom(cls))
            return new LongArrayCoder();

        if (Parcelable.class.isAssignableFrom(cls))
            return new ParcelableCoder();

        if (Parcelable[].class.isAssignableFrom(cls))
            return new ParcelableArrayCoder();

        if (Serializable.class.isAssignableFrom(cls))
            return new SerializableCoder();

        if (short.class.isAssignableFrom(cls) || Short.class.isAssignableFrom(cls))
            return new ShortCoder();

        if (short[].class.isAssignableFrom(cls))
            return new ShortArrayCoder();

        if (String.class.isAssignableFrom(cls))
            return new StringCoder();

        if (String[].class.isAssignableFrom(cls))
            return new StringArrayCoder();

        final int apiVersion = Build.VERSION.SDK_INT;
        if (IBinder.class.isAssignableFrom(cls)) {
            if (apiVersion >= Build.VERSION_CODES.JELLY_BEAN_MR2)
                return new IBinderCoder();

            throw new RuntimeException("The class " + IBinderCoder.class.getName() + " is available only above api 18");
        }

        if (Size.class.isAssignableFrom(cls)) {
            if (apiVersion >= Build.VERSION_CODES.LOLLIPOP)
                return new SizeCoder();

            throw new RuntimeException("The class " + SizeCoder.class.getName() + " is available only above api 21");
        }

        if (SizeF.class.isAssignableFrom(cls)) {
            if (apiVersion >= Build.VERSION_CODES.LOLLIPOP)
                return new SizeFCoder();

            throw new RuntimeException("The class " + SizeFCoder.class.getName() + " is available only above api 21");
        }

        return null;
    }
}
