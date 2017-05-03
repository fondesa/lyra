package com.fondesa.lyra.sample.model

import android.os.Parcel
import android.os.Parcelable

class ParcelableModel : Parcelable {
    var id: Int = 0
    var value: String

    constructor(id: Int, value: String) {
        this.id = id
        this.value = value
    }

    private constructor(parcel: Parcel) {
        id = parcel.readInt()
        value = parcel.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        val CREATOR: Parcelable.Creator<ParcelableModel> = object : Parcelable.Creator<ParcelableModel> {
            override fun createFromParcel(parcel: Parcel): ParcelableModel {
                return ParcelableModel(parcel)
            }

            override fun newArray(size: Int): Array<ParcelableModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
