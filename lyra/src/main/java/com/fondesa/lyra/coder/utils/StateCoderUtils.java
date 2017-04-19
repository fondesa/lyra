/*
 * Copyright (c) 2017 Fondesa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fondesa.lyra.coder.utils;

import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Size;
import android.util.SizeF;

import com.fondesa.lyra.coder.StateCoder;
import com.fondesa.lyra.coder.base.BooleanArrayCoder;
import com.fondesa.lyra.coder.base.BooleanCoder;
import com.fondesa.lyra.coder.base.ByteArrayCoder;
import com.fondesa.lyra.coder.base.ByteCoder;
import com.fondesa.lyra.coder.base.CharArrayCoder;
import com.fondesa.lyra.coder.base.CharCoder;
import com.fondesa.lyra.coder.base.CharSequenceArrayCoder;
import com.fondesa.lyra.coder.base.CharSequenceCoder;
import com.fondesa.lyra.coder.base.DoubleArrayCoder;
import com.fondesa.lyra.coder.base.DoubleCoder;
import com.fondesa.lyra.coder.base.FloatArrayCoder;
import com.fondesa.lyra.coder.base.FloatCoder;
import com.fondesa.lyra.coder.base.IBinderCoder;
import com.fondesa.lyra.coder.base.IntArrayCoder;
import com.fondesa.lyra.coder.base.IntCoder;
import com.fondesa.lyra.coder.base.LongArrayCoder;
import com.fondesa.lyra.coder.base.LongCoder;
import com.fondesa.lyra.coder.base.ParcelableArrayCoder;
import com.fondesa.lyra.coder.base.ParcelableCoder;
import com.fondesa.lyra.coder.base.SerializableCoder;
import com.fondesa.lyra.coder.base.ShortArrayCoder;
import com.fondesa.lyra.coder.base.ShortCoder;
import com.fondesa.lyra.coder.base.SizeCoder;
import com.fondesa.lyra.coder.base.SizeFCoder;
import com.fondesa.lyra.coder.base.StringArrayCoder;
import com.fondesa.lyra.coder.base.StringCoder;
import com.fondesa.lyra.exception.CoderNotFoundException;

import java.io.Serializable;

/**
 * Utilities class to get a {@link StateCoder} from a class.
 * <br>
 * Only default coders are used in this class.
 */
public final class StateCoderUtils {

    private StateCoderUtils() {
        // empty private constructor to avoid instantiation
    }

    /**
     * Get the default {@link StateCoder} from a class.
     * If the class is equal to the coder class or the coder class extends/implements the class
     * passed as parameter, the class is supported.
     * In the other cases, you have to implement your own {@link StateCoder}.
     *
     * @param cls class that can be serialized/deserialized with a {@link StateCoder}
     * @return coder related to the class passed as parameter
     * @throws CoderNotFoundException if the coder wasn't found
     */
    @NonNull
    public static StateCoder getBasicCoderForClass(@NonNull Class<?> cls) throws CoderNotFoundException {
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
            if (Size.class == cls)
                return new SizeCoder();

            if (SizeF.class == cls)
                return new SizeFCoder();
        }

        return getBasicCoderForInheritedClass(cls);
    }

    /**
     * Get the {@link StateCoder} if the class passed as parameter is a subclass of the supported ones.
     *
     * @param cls class that can be serialized/deserialized with a {@link StateCoder}
     * @return coder related to the class passed as parameter
     * @throws CoderNotFoundException if the coder wasn't found
     */
    @NonNull
    private static StateCoder getBasicCoderForInheritedClass(@NonNull Class<?> cls) throws CoderNotFoundException {
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

        throw new CoderNotFoundException("You have to specify a custom " + StateCoder.class.getName() +
                " for an object of type " + cls.getName());
    }
}