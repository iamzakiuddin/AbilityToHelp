package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class ConvoResponse(
    @SerializedName("results")
    val results: List<ConvoResult>
)