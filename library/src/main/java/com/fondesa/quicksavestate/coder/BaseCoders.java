package com.fondesa.quicksavestate.coder;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Size;
import android.util.SizeF;

import java.io.Serializable;

/**
 * Created by antoniolig on 18/02/17.
 */
public class BaseCoders {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static class BinderCoder extends BaseCoder<IBinder> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull IBinder fieldValue) {
            state.putBinder(fieldName, fieldValue);
        }
    }

    public static class BooleanCoder extends BaseCoder<Boolean> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Boolean fieldValue) {
            state.putBoolean(fieldName, fieldValue);
        }
    }

    public static class BooleanArrayCoder extends BaseCoder<boolean[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull boolean[] fieldValue) {
            state.putBooleanArray(fieldName, fieldValue);
        }
    }

    public static class ByteCoder extends BaseCoder<Byte> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Byte fieldValue) {
            state.putByte(fieldName, fieldValue);
        }
    }

    public static class ByteArrayCoder extends BaseCoder<byte[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull byte[] fieldValue) {
            state.putByteArray(fieldName, fieldValue);
        }
    }

    public static class CharCoder extends BaseCoder<Character> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Character fieldValue) {
            state.putChar(fieldName, fieldValue);
        }
    }

    public static class CharArrayCoder extends BaseCoder<char[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull char[] fieldValue) {
            state.putCharArray(fieldName, fieldValue);
        }
    }

    public static class CharSequenceCoder extends BaseCoder<CharSequence> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull CharSequence fieldValue) {
            state.putCharSequence(fieldName, fieldValue);
        }
    }

    public static class CharSequenceArrayCoder extends BaseCoder<CharSequence[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull CharSequence[] fieldValue) {
            state.putCharSequenceArray(fieldName, fieldValue);
        }
    }

    public static class DoubleCoder extends BaseCoder<Double> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Double fieldValue) {
            state.putDouble(fieldName, fieldValue);
        }
    }

    public static class DoubleArrayCoder extends BaseCoder<double[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull double[] fieldValue) {
            state.putDoubleArray(fieldName, fieldValue);
        }
    }

    public static class FloatCoder extends BaseCoder<Float> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Float fieldValue) {
            state.putFloat(fieldName, fieldValue);
        }
    }

    public static class FloatArrayCoder extends BaseCoder<float[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull float[] fieldValue) {
            state.putFloatArray(fieldName, fieldValue);
        }
    }

    public static class IntCoder extends BaseCoder<Integer> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Integer fieldValue) {
            state.putInt(fieldName, fieldValue);
        }
    }

    public static class IntArrayCoder extends BaseCoder<int[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull int[] fieldValue) {
            state.putIntArray(fieldName, fieldValue);
        }
    }

    public static class LongCoder extends BaseCoder<Long> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Long fieldValue) {
            state.putLong(fieldName, fieldValue);
        }
    }

    public static class LongArrayCoder extends BaseCoder<long[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull long[] fieldValue) {
            state.putLongArray(fieldName, fieldValue);
        }
    }

    public static class ParcelableCoder extends BaseCoder<Parcelable> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Parcelable fieldValue) {
            state.putParcelable(fieldName, fieldValue);
        }
    }

    public static class ParcelableArrayCoder extends BaseCoder<Parcelable[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Parcelable[] fieldValue) {
            state.putParcelableArray(fieldName, fieldValue);
        }
    }

    public static class SerializableCoder extends BaseCoder<Serializable> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Serializable fieldValue) {
            state.putSerializable(fieldName, fieldValue);
        }
    }

    public static class ShortCoder extends BaseCoder<Short> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Short fieldValue) {
            state.putShort(fieldName, fieldValue);
        }
    }

    public static class ShortArrayCoder extends BaseCoder<short[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull short[] fieldValue) {
            state.putShortArray(fieldName, fieldValue);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static class SizeCoder extends BaseCoder<Size> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Size fieldValue) {
            state.putSize(fieldName, fieldValue);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static class SizeFSD extends BaseCoder<SizeF> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull SizeF fieldValue) {
            state.putSizeF(fieldName, fieldValue);
        }
    }

    public static class StringCoder extends BaseCoder<String> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull String fieldValue) {
            state.putString(fieldName, fieldValue);
        }
    }

    public static class StringArrayCoder extends BaseCoder<String[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull String[] fieldValue) {
            state.putStringArray(fieldName, fieldValue);
        }
    }

    private abstract static class BaseCoder<FieldType> implements StateCoder<FieldType> {
        @Override
        public FieldType deserialize(@NonNull Bundle manager, @NonNull String fieldName) {
            //noinspection unchecked
            return (FieldType) manager.get(fieldName);
        }
    }
}