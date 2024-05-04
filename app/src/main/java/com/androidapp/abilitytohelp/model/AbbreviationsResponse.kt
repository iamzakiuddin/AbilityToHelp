package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class AbbreviationsResponse(
    @SerializedName("result")
    val result: List<AbbreviationsResult>
)