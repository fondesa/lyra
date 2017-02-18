package com.fondesa.quicksavestate;

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
public class NativeSD {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static class BinderSD extends BaseSD<IBinder> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull IBinder fieldValue) {
            state.putBinder(fieldName, fieldValue);
        }
    }

    public static class BooleanSD extends BaseSD<Boolean> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Boolean fieldValue) {
            state.putBoolean(fieldName, fieldValue);
        }
    }

    public static class BooleanArraySD extends BaseSD<boolean[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull boolean[] fieldValue) {
            state.putBooleanArray(fieldName, fieldValue);
        }
    }

    public static class ByteSD extends BaseSD<Byte> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Byte fieldValue) {
            state.putByte(fieldName, fieldValue);
        }
    }

    public static class ByteArraySD extends BaseSD<byte[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull byte[] fieldValue) {
            state.putByteArray(fieldName, fieldValue);
        }
    }

    public static class CharSD extends BaseSD<Character> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Character fieldValue) {
            state.putChar(fieldName, fieldValue);
        }
    }

    public static class CharArraySD extends BaseSD<char[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull char[] fieldValue) {
            state.putCharArray(fieldName, fieldValue);
        }
    }

    public static class CharSequenceSD extends BaseSD<CharSequence> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull CharSequence fieldValue) {
            state.putCharSequence(fieldName, fieldValue);
        }
    }

    public static class CharSequenceArraySD extends BaseSD<CharSequence[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull CharSequence[] fieldValue) {
            state.putCharSequenceArray(fieldName, fieldValue);
        }
    }

    public static class DoubleSD extends BaseSD<Double> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Double fieldValue) {
            state.putDouble(fieldName, fieldValue);
        }
    }

    public static class DoubleArraySD extends BaseSD<double[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull double[] fieldValue) {
            state.putDoubleArray(fieldName, fieldValue);
        }
    }

    public static class FloatSD extends BaseSD<Float> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Float fieldValue) {
            state.putFloat(fieldName, fieldValue);
        }
    }

    public static class FloatArraySD extends BaseSD<float[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull float[] fieldValue) {
            state.putFloatArray(fieldName, fieldValue);
        }
    }

    public static class IntSD extends BaseSD<Integer> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Integer fieldValue) {
            state.putInt(fieldName, fieldValue);
        }
    }

    public static class IntArraySD extends BaseSD<int[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull int[] fieldValue) {
            state.putIntArray(fieldName, fieldValue);
        }
    }

    public static class LongSD extends BaseSD<Long> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Long fieldValue) {
            state.putLong(fieldName, fieldValue);
        }
    }

    public static class LongArraySD extends BaseSD<long[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull long[] fieldValue) {
            state.putLongArray(fieldName, fieldValue);
        }
    }

    public static class ParcelableSD extends BaseSD<Parcelable> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Parcelable fieldValue) {
            state.putParcelable(fieldName, fieldValue);
        }
    }

    public static class ParcelableArraySD extends BaseSD<Parcelable[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Parcelable[] fieldValue) {
            state.putParcelableArray(fieldName, fieldValue);
        }
    }

    public static class SerializableSD extends BaseSD<Serializable> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Serializable fieldValue) {
            state.putSerializable(fieldName, fieldValue);
        }
    }

    public static class ShortSD extends BaseSD<Short> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Short fieldValue) {
            state.putShort(fieldName, fieldValue);
        }
    }

    public static class ShortArraySD extends BaseSD<short[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull short[] fieldValue) {
            state.putShortArray(fieldName, fieldValue);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static class SizeSD extends BaseSD<Size> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull Size fieldValue) {
            state.putSize(fieldName, fieldValue);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static class SizeFSD extends BaseSD<SizeF> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull SizeF fieldValue) {
            state.putSizeF(fieldName, fieldValue);
        }
    }

    public static class StringSD extends BaseSD<String> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull String fieldValue) {
            state.putString(fieldName, fieldValue);
        }
    }

    public static class StringArraySD extends BaseSD<String[]> {
        @Override
        public void serialize(@NonNull Bundle state, @NonNull String fieldName, @NonNull String[] fieldValue) {
            state.putStringArray(fieldName, fieldValue);
        }
    }

    private abstract static class BaseSD<T> implements StateSD<T> {
        @Override
        public T deserialize(@NonNull Bundle manager, @NonNull String fieldName) {
            //noinspection unchecked
            return (T) manager.get(fieldName);
        }
    }
}