package com.androidapp.abilitytohelp.model

import com.google.gson.annotations.SerializedName

data class DictonaryResult(
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String,
    @SerializedName("partofspeech")
    val partofspeech: String,
    @SerializedName("term")
    val term: String
)