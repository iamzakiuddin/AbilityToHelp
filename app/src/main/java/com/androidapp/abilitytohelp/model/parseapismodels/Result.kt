package com.androidapp.abilitytohelp.model.parseapismodels


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("Desc_Data")
    val descData: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    var isTrue : Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(Image::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(createdAt)
        parcel.writeString(descData)
        parcel.writeParcelable(image, flags)
        parcel.writeString(objectId)
        parcel.writeString(title)
        parcel.writeString(updatedAt)
        parcel.writeInt(isTrue)
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