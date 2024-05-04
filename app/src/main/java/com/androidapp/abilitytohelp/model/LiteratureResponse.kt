package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class LiteratureResponse(
    @SerializedName("result")
    val result: List<LiteratureResult>
)