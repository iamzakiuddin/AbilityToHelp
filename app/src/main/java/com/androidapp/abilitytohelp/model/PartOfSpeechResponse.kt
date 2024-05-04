package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class PartOfSpeechResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("other_part_of_speech")
    val otherPartOfSpeech: OtherPartOfSpeech,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("word")
    val word: String
)