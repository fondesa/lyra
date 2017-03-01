package com.fondesa.quicksavestate.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by antoniolig on 01/03/17.
 */

public class TestModels {
    private TestModels() {
        // private empty constructor to avoid instantiation
    }

    public static class ImplementedCharSequence implements CharSequence {
        @Override
        public int length() {
            return 5;
        }

        @Override
        public char charAt(int index) {
            return 'x';
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return "test";
        }
    }

    public static class ImplementedParcelable implements Parcelable {
        public String firstParam;
        public String secondParam;
        public int thirdParam;

        public static ImplementedParcelable getDefault() {
            return new ImplementedParcelable("first", "second", 3);
        }

        public ImplementedParcelable(String firstParam, String secondParam, int thirdParam) {
            this.firstParam = firstParam;
            this.secondParam = secondParam;
            this.thirdParam = thirdParam;
        }

        protected ImplementedParcelable(Parcel in) {
            firstParam = in.readString();
            secondParam = in.readString();
            thirdParam = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(firstParam);
            dest.writeString(secondParam);
            dest.writeInt(thirdParam);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ImplementedParcelable> CREATOR = new Creator<ImplementedParcelable>() {
            @Override
            public ImplementedParcelable createFromParcel(Parcel in) {
                return new ImplementedParcelable(in);
            }

            @Override
            public ImplementedParcelable[] newArray(int size) {
                return new ImplementedParcelable[size];
            }
        };
    }

    public static class ImplementedSerializable implements Serializable {
        private static final long serialVersionUID = -1315746751902301329L;

        public String firstParam;
        public String secondParam;
        public int thirdParam;

        public static ImplementedSerializable getDefault() {
            return new ImplementedSerializable("first", "second", 3);
        }

        public ImplementedSerializable(String firstParam, String secondParam, int thirdParam) {
            this.firstParam = firstParam;
            this.secondParam = secondParam;
            this.thirdParam = thirdParam;
        }
    }
}
