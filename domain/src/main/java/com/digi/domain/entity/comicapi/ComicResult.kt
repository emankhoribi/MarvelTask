package com.digi.domain.entity.comicapi

import android.os.Parcel
import android.os.Parcelable

data class ComicResult(
    val description: String,
    val diamondCode: String,
    val digitalId: Int,
    val ean: String,
    val format: String,
    val id: Int,
    val isbn: String,
    val issn: String,
    val issueNumber: Int,
    val modified: String,
    val pageCount: Int,
    val resourceURI: String,
    val thumbnail: Thumbnail?,
    val title: String,
    val upc: String,
    val variantDescription: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readParcelable(Thumbnail::class.java.classLoader),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(diamondCode)
        parcel.writeInt(digitalId)
        parcel.writeString(ean)
        parcel.writeString(format)
        parcel.writeInt(id)
        parcel.writeString(isbn)
        parcel.writeString(issn)
        parcel.writeInt(issueNumber)
        parcel.writeString(modified)
        parcel.writeInt(pageCount)
        parcel.writeString(resourceURI)
        parcel.writeParcelable(thumbnail, flags)
        parcel.writeString(title)
        parcel.writeString(upc)
        parcel.writeString(variantDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicResult> {
        override fun createFromParcel(parcel: Parcel): ComicResult {
            return ComicResult(parcel)
        }

        override fun newArray(size: Int): Array<ComicResult?> {
            return arrayOfNulls(size)
        }
    }
}