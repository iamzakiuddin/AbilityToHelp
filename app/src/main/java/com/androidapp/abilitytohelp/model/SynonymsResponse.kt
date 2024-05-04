package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class SynonymsResponse(
    @SerializedName("synonyms")
    val synonyms: List<String>,
    @SerializedName("word")
    val word: String,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: String
)