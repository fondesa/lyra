package com.fondesa.quicksavestate;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Size;
import android.util.SizeF;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by antoniolig on 17/02/17.
 */
public class SaveStateProcessor {
    private ArrayMap<Class<?>, StateSD<?>> mNativeCachedState;

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
                final StateSD stateSD = getStateSD(field, saveState);
                stateSD.serialize(bundle, fieldName, fieldValue);
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

            final StateSD stateSD = getStateSD(field, saveState);

            final String fieldName = field.getName();
            final Object fieldValue = stateSD.deserialize(bundle, fieldName);
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

    private StateSD getStateSD(@NonNull Field field, @NonNull SaveState saveState) {
        final Class<?> fieldClass = field.getType();
        StateSD stateSD;

        final Class<? extends StateSD> stateSDClass = saveState.value();
        if (stateSDClass == StateSD.class) {
            stateSD = mNativeCachedState.get(fieldClass);
            if (stateSD != null)
                return stateSD;

            stateSD = getNativeStateSD(fieldClass);
            if (stateSD == null) {
                throw new RuntimeException("You have to specify a custom " + StateSD.class.getName() +
                        " for an object of type " + fieldClass.getName());
            }
            mNativeCachedState.put(fieldClass, stateSD);
        } else {
            try {
                stateSD = stateSDClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Cannot instantiate a " + StateSD.class.getSimpleName() +
                        " of class " + stateSDClass.getName());
            }
        }
        return stateSD;
    }

    private StateSD getNativeStateSD(@NonNull Class<?> cls) {
        if (boolean.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls))
            return new NativeSD.BooleanSD();

        if (boolean[].class.isAssignableFrom(cls))
            return new NativeSD.BooleanArraySD();

        if (byte.class.isAssignableFrom(cls) || Byte.class.isAssignableFrom(cls))
            return new NativeSD.ByteSD();

        if (byte[].class.isAssignableFrom(cls))
            return new NativeSD.ByteArraySD();

        if (char.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls))
            return new NativeSD.CharSD();

        if (char[].class.isAssignableFrom(cls))
            return new NativeSD.CharArraySD();

        if (CharSequence.class.isAssignableFrom(cls))
            return new NativeSD.CharSequenceSD();

        if (CharSequence[].class.isAssignableFrom(cls))
            return new NativeSD.CharSequenceArraySD();

        if (double.class.isAssignableFrom(cls) || Double.class.isAssignableFrom(cls))
            return new NativeSD.DoubleSD();

        if (double[].class.isAssignableFrom(cls))
            return new NativeSD.DoubleArraySD();

        if (float.class.isAssignableFrom(cls) || Float.class.isAssignableFrom(cls))
            return new NativeSD.FloatSD();

        if (float[].class.isAssignableFrom(cls))
            return new NativeSD.FloatArraySD();

        if (int.class.isAssignableFrom(cls) || Integer.class.isAssignableFrom(cls))
            return new NativeSD.IntSD();

        if (int[].class.isAssignableFrom(cls) || Integer[].class.isAssignableFrom(cls))
            return new NativeSD.IntArraySD();

        if (long.class.isAssignableFrom(cls) || Long.class.isAssignableFrom(cls))
            return new NativeSD.LongSD();

        if (long[].class.isAssignableFrom(cls))
            return new NativeSD.LongArraySD();

        if (Parcelable.class.isAssignableFrom(cls))
            return new NativeSD.ParcelableSD();

        if (Parcelable[].class.isAssignableFrom(cls))
            return new NativeSD.ParcelableArraySD();

        if (Serializable.class.isAssignableFrom(cls))
            return new NativeSD.SerializableSD();

        if (short.class.isAssignableFrom(cls) || Short.class.isAssignableFrom(cls))
            return new NativeSD.ShortSD();

        if (short[].class.isAssignableFrom(cls))
            return new NativeSD.ShortArraySD();

        if (String.class.isAssignableFrom(cls))
            return new NativeSD.StringSD();

        if (String[].class.isAssignableFrom(cls))
            return new NativeSD.StringArraySD();

        final int apiVersion = Build.VERSION.SDK_INT;
        if (IBinder.class.isAssignableFrom(cls)) {
            if (apiVersion >= Build.VERSION_CODES.JELLY_BEAN_MR2)
                return new NativeSD.BinderSD();

            throw new RuntimeException("The class " + NativeSD.BinderSD.class.getName() + " is available only above api 18");
        }

        if (Size.class.isAssignableFrom(cls)) {
            if (apiVersion >= Build.VERSION_CODES.LOLLIPOP)
                return new NativeSD.SizeSD();

            throw new RuntimeException("The class " + NativeSD.SizeSD.class.getName() + " is available only above api 21");
        }

        if (SizeF.class.isAssignableFrom(cls)) {
            if (apiVersion >= Build.VERSION_CODES.LOLLIPOP)
                return new NativeSD.SizeFSD();

            throw new RuntimeException("The class " + NativeSD.SizeFSD.class.getName() + " is available only above api 21");
        }

        return null;
    }
}