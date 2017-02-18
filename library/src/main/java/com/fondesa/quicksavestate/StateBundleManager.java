//package com.fondesa.quicksavestate;
//
//import android.os.Build;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.annotation.RequiresApi;
//
//import java.util.ArrayList;
//
///**
// * Created by antoniolig on 17/02/17.
// */
//
//public class StateBundleManager {
//    private static final String BUNDLE_PREFIX = "ots";
//
//    private Bundle mBundle;
//
//    public StateBundleManager(@NonNull Bundle bundle) {
//        mBundle = bundle;
//    }
//
//    public void putString(@NonNull String key, @Nullable String value) {
//        mBundle.putString(fullKey(key), value);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
//    public void putBinder(@NonNull String key, @Nullable IBinder value) {
//        mBundle.putBinder(key, value);
//    }
//
//    public void putBundle(@NonNull String key, @Nullable Bundle value) {
//        mBundle.putBundle(fullKey(key), value);
//    }
//
//    public void putByte(@NonNull String key, Byte value) {
//        if (value != null) {
//            mBundle.putByte(fullKey(key), value);
//        }
//    }
//
//    public void putByteArray(@NonNull String key, @Nullable byte[] value) {
//        mBundle.putByteArray(fullKey(key), value);
//    }
//
//    public void putChar(@NonNull String key, Character value) {
//        if (value != null) {
//            mBundle.putChar(fullKey(key), value);
//        }
//    }
//
//    public void putCharArray(@NonNull String key, @Nullable char[] value) {
//        mBundle.putCharArray(fullKey(key), value);
//    }
//
//    public void putCharSequence(@NonNull String key, @Nullable CharSequence value) {
//        mBundle.putCharSequence(fullKey(key), value);
//    }
//
//    public void putCharSequenceArray(@NonNull String key, @Nullable CharSequence[] value) {
//        mBundle.putCharSequenceArray(fullKey(key), value);
//    }
//
//    public void putCharSequenceArrayList(@NonNull String key, @Nullable ArrayList<CharSequence> value) {
//        mBundle.putCharSequenceArrayList(fullKey(key), value);
//    }
//
//    public void putFloat(@NonNull String key, @Nullable Float value) {
//        if (value != null) {
//            mBundle.putFloat(fullKey(key), value);
//        }
//    }
//
//    public void putFloatArray(@NonNull String key, @Nullable float[] value) {
//        mBundle.putFloatArray(fullKey(key), value);
//    }
//
//    public void putIntegerArrayList(@NonNull String key, @Nullable ArrayList<Integer> value) {
//        mBundle.putIntegerArrayList(fullKey(key), value);
//    }
//
//
//
////    public void putByteObjArray(@NonNull String key, @Nullable Byte[] value) {
////        if (value != null) {
////            int valueLength = value.length;
////            byte[] byteArr = new byte[valueLength];
////            for (int i = 0; i < valueLength; i++) {
////                Byte byteObj = value[i];
////                if (byteObj == null) {
////                    byteArr[i] =
////                }
////            }
////        }
////        mBundle.putString(fullKey(key), value);
////    }
//
//    @Nullable
//    public Object get(@NonNull String fieldName) {
//        return mBundle.get(fullKey(fieldName));
//    }
//
//    private static String fullKey(@NonNull String fieldName) {
//        return BUNDLE_PREFIX + fieldName;
//    }
//}
