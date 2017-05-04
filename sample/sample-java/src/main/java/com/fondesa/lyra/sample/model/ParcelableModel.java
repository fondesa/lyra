package com.fondesa.lyra.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableModel implements Parcelable {
    public int id;
    public String value;

    public ParcelableModel(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private ParcelableModel(Parcel in) {
        id = in.readInt();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelableModel> CREATOR = new Creator<ParcelableModel>() {
        @Override
        public ParcelableModel createFromParcel(Parcel in) {
            return new ParcelableModel(in);
        }

        @Override
        public ParcelableModel[] newArray(int size) {
            return new ParcelableModel[size];
        }
    };
}