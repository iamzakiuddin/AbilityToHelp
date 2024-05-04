package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class LiteratureResult(
    @SerializedName("link")
    val link: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("writer")
    val writer: String
)