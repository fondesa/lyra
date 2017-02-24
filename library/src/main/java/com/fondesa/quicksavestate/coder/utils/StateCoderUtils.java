package com.fondesa.quicksavestate.coder.utils;

import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.fondesa.quicksavestate.exception.CoderNotFoundException;

import java.io.Serializable;

/**
 * Created by antoniolig on 22/02/17.
 */

public final class StateCoderUtils {

    private StateCoderUtils() {
        // empty private constructor to avoid instantiation
    }

    @NonNull
    public static StateCoder getBaseCoderForClass(@NonNull Class<?> cls) throws CoderNotFoundException {
        if (boolean.class == cls || Boolean.class == cls)
            return new BooleanCoder();

        if (boolean[].class == cls)
            return new BooleanArrayCoder();

        if (byte.class == cls || Byte.class == cls)
            return new ByteCoder();

        if (byte[].class == cls)
            return new ByteArrayCoder();

        if (char.class == cls || Character.class == cls)
            return new CharCoder();

        if (char[].class == cls)
            return new CharArrayCoder();

        if (CharSequence.class == cls)
            return new CharSequenceCoder();

        if (CharSequence[].class == cls)
            return new CharSequenceArrayCoder();

        if (double.class == cls || Double.class == cls)
            return new DoubleCoder();

        if (double[].class == cls)
            return new DoubleArrayCoder();

        if (float.class == cls || Float.class == cls)
            return new FloatCoder();

        if (float[].class == cls)
            return new FloatArrayCoder();

        if (IBinder.class == cls)
            return new IBinderCoder();

        if (int.class == cls || Integer.class == cls)
            return new IntCoder();

        if (int[].class == cls)
            return new IntArrayCoder();

        if (long.class == cls || Long.class == cls)
            return new LongCoder();

        if (long[].class == cls)
            return new LongArrayCoder();

        if (Parcelable.class == cls)
            return new ParcelableCoder();

        if (Parcelable[].class == cls)
            return new ParcelableArrayCoder();

        if (Serializable.class == cls)
            return new SerializableCoder();

        if (short.class == cls || Short.class == cls)
            return new ShortCoder();

        if (short[].class == cls)
            return new ShortArrayCoder();

        if (String.class == cls)
            return new StringCoder();

        if (String[].class == cls)
            return new StringArrayCoder();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Size.class == cls) {
                return new SizeCoder();
            }

            if (SizeF.class == cls) {
                return new SizeFCoder();
            }
        }

        StateCoder inheritedCoder = getBaseCoderForInheritedClass(cls);
        if (inheritedCoder != null)
            return inheritedCoder;

        throw new CoderNotFoundException("You have to specify a custom " + StateCoder.class.getName() +
                " for an object of type " + cls.getName());
    }


    @Nullable
    private static StateCoder getBaseCoderForInheritedClass(@NonNull Class<?> cls) {
        if (CharSequence.class.isAssignableFrom(cls))
            return new CharSequenceCoder();

        if (CharSequence[].class.isAssignableFrom(cls))
            return new CharSequenceArrayCoder();

        if (IBinder.class.isAssignableFrom(cls))
            return new IBinderCoder();

        if (Parcelable.class.isAssignableFrom(cls))
            return new ParcelableCoder();

        if (Parcelable[].class.isAssignableFrom(cls))
            return new ParcelableArrayCoder();

        if (Serializable.class.isAssignableFrom(cls))
            return new SerializableCoder();

        return null;
    }
}