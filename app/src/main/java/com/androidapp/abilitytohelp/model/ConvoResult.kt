package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class ConvoResult(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("english")
    val english: String,
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("spanish")
    val spanish: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    var isSelected: Boolean = false
)