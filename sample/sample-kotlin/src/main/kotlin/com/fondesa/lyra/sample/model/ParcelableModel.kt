/*
 * Copyright (c) 2017 Fondesa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
