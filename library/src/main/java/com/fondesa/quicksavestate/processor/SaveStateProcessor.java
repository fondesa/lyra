package com.fondesa.quicksavestate.processor;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Size;
import android.util.SizeF;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.BaseCoders;
import com.fondesa.quicksavestate.coder.StateCoder;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by antoniolig on 17/02/17.
 */
public class SaveStateProcessor {
    private ArrayMap<Class<?>, StateCoder<?>> mNativeCachedState;

    public SaveStateProcessor() {
        mNativeCachedState = new ArrayMap<>();
    }

    public void saveState(@NonNull Object stateHolder, @NonNull Bundle bundle) {
        Field[] fields = stateHolder.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            SaveState saveState = field.getAnnotation(SaveState.class);
            if (saveState == null)
                continue;

            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }

            final String fieldName = field.getName();
            final Object fieldValue;
            try {
                fieldValue = field.get(stateHolder);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot access to field " + fieldName +
                        " of class " + stateHolder.getClass().getName());
            }

            if (fieldValue != null) {
                final StateCoder stateCoder = getStateSD(field, saveState);
                stateCoder.serialize(bundle, fieldName, fieldValue);
            }

            if (!accessible) {
                field.setAccessible(false);
            }
        }
    }

    public void restoreState(@NonNull Object stateHolder, @Nullable Bundle bundle) {
        if (bundle == null)
            return;

        Field[] fields = stateHolder.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            SaveState saveState = field.getAnnotation(SaveState.class);
            if (saveState == null)
                continue;

            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }

            final StateCoder stateCoder = getStateSD(field, saveState);

            final String fieldName = field.getName();
            final Object fieldValue = stateCoder.deserialize(bundle, fieldName);
            if (fieldValue != null) {
                try {
                    field.set(stateHolder, fieldValue);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot access to field " + fieldName +
                            " of class " + stateHolder.getClass().getName());
                }
            }

            if (!accessible) {
                field.setAccessible(false);
            }
        }
    }

    private StateCoder getStateSD(@NonNull Field field, @NonNull SaveState saveState) {
        final Class<?> fieldClass = field.getType();
        StateCoder stateCoder;

        final Class<? extends StateCoder> stateSDClass = saveState.value();
        if (stateSDClass == StateCoder.class) {
            stateCoder = mNativeCachedState.get(fieldClass);
            if (stateCoder != null)
                return stateCoder;

            stateCoder = getNativeStateSD(fieldClass);
            if (stateCoder == null) {
                throw new RuntimeException("You have to specify a custom " + StateCoder.class.getName() +
                        " for an object of type " + fieldClass.getName());
            }
            mNativeCachedState.put(fieldClass, stateCoder);
        } else {
            try {
                stateCoder = stateSDClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Cannot instantiate a " + StateCoder.class.getSimpleName() +
                        " of class " + stateSDClass.getName());
            }
        }
        return stateCoder;
    }

    private StateCoder getNativeStateSD(@NonNull Class<?> cls) {
        if (boolean.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls))
            return new BaseCoders.BooleanCoder();

        if (boolean[].class.isAssignableFrom(cls))
            return new BaseCoders.BooleanArrayCoder();

        if (byte.class.isAssignableFrom(cls) || Byte.class.isAssignableFrom(cls))
            return new BaseCoders.ByteCoder();

        if (byte[].class.isAssignableFrom(cls))
            return new BaseCoders.ByteArrayCoder();

        if (char.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls))
            return new BaseCoders.CharCoder();

        if (char[].class.isAssignableFrom(cls))
            return new BaseCoders.CharArrayCoder();

        if (CharSequence.class.isAssignableFrom(cls))
            return new BaseCoders.CharSequenceCoder();

        if (CharSequence[].class.isAssignableFrom(cls))
            return new BaseCoders.CharSequenceArrayCoder();

        if (double.class.isAssignableFrom(cls) || Double.class.isAssignableFrom(cls))
            return new BaseCoders.DoubleCoder();

        if (double[].class.isAssignableFrom(cls))
            return new BaseCoders.DoubleArrayCoder();

        if (float.class.isAssignableFrom(cls) || Float.class.isAssignableFrom(cls))
            return new BaseCoders.FloatCoder();

        if (float[].class.isAssignableFrom(cls))
            return new BaseCoders.FloatArrayCoder();

        if (int.class.isAssignableFrom(cls) || Integer.class.isAssignableFrom(cls))
            return new BaseCoders.IntCoder();

        if (int[].class.isAssignableFrom(cls) || Integer[].class.isAssignableFrom(cls))
            return new BaseCoders.IntArrayCoder();

        if (long.class.isAssignableFrom(cls) || Long.class.isAssignableFrom(cls))
            return new BaseCoders.LongCoder();

        if (long[].class.isAssignableFrom(cls))
            return new BaseCoders.LongArrayCoder();

        if (Parcelable.class.isAssignableFrom(cls))
            return new BaseCoders.ParcelableCoder();

        if (Parcelable[].class.isAssignableFrom(cls))
            return new BaseCoders.ParcelableArrayCoder();

        if (Serializable.class.isAssignableFrom(cls))
            return new BaseCoders.SerializableCoder();

        if (short.class.isAssignableFrom(cls) || Short.class.isAssignableFrom(cls))
            return new BaseCoders.ShortCoder();

        if (short[].class.isAssignableFrom(cls))
            return new BaseCoders.ShortArrayCoder();

        if (String.class.isAssignableFrom(cls))
            return new BaseCoders.StringCoder();

        if (String[].class.isAssignableFrom(cls))
            return new BaseCoders.StringArrayCoder();

        final int apiVersion = Build.VERSION.SDK_INT;
        if (IBinder.class.isAssignableFrom(cls)) {
            if (apiVersion >= Build.VERSION_CODES.JELLY_BEAN_MR2)
                return new BaseCoders.BinderCoder();

            throw new RuntimeException("The class " + BaseCoders.BinderCoder.class.getName() + " is available only above api 18");
        }

        if (Size.class.isAssignableFrom(cls)) {
            if (apiVersion >= Build.VERSION_CODES.LOLLIPOP)
                return new BaseCoders.SizeCoder();

            throw new RuntimeException("The class " + BaseCoders.SizeCoder.class.getName() + " is available only above api 21");
        }

        if (SizeF.class.isAssignableFrom(cls)) {
            if (apiVersion >= Build.VERSION_CODES.LOLLIPOP)
                return new BaseCoders.SizeFSD();

            throw new RuntimeException("The class " + BaseCoders.SizeFSD.class.getName() + " is available only above api 21");
        }

        return null;
    }
}