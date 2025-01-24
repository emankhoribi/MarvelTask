package com.digi.domain.entity

import android.os.Parcel
import android.os.Parcelable

data class ItemXXX(
    val name: String,
    val resourceURI: String,
    val type: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(resourceURI)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemXXX> {
        override fun createFromParcel(parcel: Parcel): ItemXXX {
            return ItemXXX(parcel)
        }

        override fun newArray(size: Int): Array<ItemXXX?> {
            return arrayOfNulls(size)
        }
    }
}