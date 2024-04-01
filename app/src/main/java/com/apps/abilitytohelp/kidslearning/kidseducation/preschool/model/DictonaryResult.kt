package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model

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