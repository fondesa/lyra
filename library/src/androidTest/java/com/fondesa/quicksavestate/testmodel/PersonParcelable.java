package com.fondesa.quicksavestate.testmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by antoniolig on 24/02/17.
 */
public class PersonParcelable implements Parcelable, Serializable {
    public String name;
    public String surname;
    public int age;

    public PersonParcelable(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    protected PersonParcelable(Parcel in) {
        name = in.readString();
        surname = in.readString();
        age = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonParcelable> CREATOR = new Creator<PersonParcelable>() {
        @Override
        public PersonParcelable createFromParcel(Parcel in) {
            return new PersonParcelable(in);
        }

        @Override
        public PersonParcelable[] newArray(int size) {
            return new PersonParcelable[size];
        }
    };
}