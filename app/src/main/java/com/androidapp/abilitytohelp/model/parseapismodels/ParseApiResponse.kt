package com.androidapp.abilitytohelp.model.parseapismodels


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ParseApiResponse(
    @SerializedName("results")
    val results: List<Result>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        mutableListOf<Result>().apply {
            parcel.readList(this, Result::class.java.classLoader)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParseApiResponse> {
        override fun createFromParcel(parcel: Parcel): ParseApiResponse {
            return ParseApiResponse(parcel)
        }

        override fun newArray(size: Int): Array<ParseApiResponse?> {
            return arrayOfNulls(size)
        }
    }
}