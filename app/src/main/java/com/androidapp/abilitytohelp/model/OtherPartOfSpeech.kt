package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class OtherPartOfSpeech(
    @SerializedName("adjective")
    val adjective: String,
    @SerializedName("adverb")
    val adverb: String,
    @SerializedName("verb")
    val verb: String
)