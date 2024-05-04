package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class PhrasesResult(
    @SerializedName("example")
    val example: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("term")
    val term: String
)