package com.digi.domain.entity

import android.os.Parcel
import android.os.Parcelable


data class Result(
    val comics: Comics?,
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnail: Thumbnail?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Comics::class.java.classLoader),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(Thumbnail::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(comics, flags)
        parcel.writeString(description)
        parcel.writeInt(id)
        parcel.writeString(modified)
        parcel.writeString(name)
        parcel.writeString(resourceURI)
        parcel.writeParcelable(thumbnail, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}