package com.fondesa.quicksavestate.testhelper.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by antoniolig on 24/02/17.
 */
public class ImplementedParcelable implements Parcelable {
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