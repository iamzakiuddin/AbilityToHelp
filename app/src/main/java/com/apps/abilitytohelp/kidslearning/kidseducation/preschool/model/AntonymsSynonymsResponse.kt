package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model


import com.google.gson.annotations.SerializedName

data class AntonymsSynonymsResponse(
    @SerializedName("antonyms")
    val antonyms: List<String>,
    @SerializedName("synonyms")
    val synonyms: List<String>,
    @SerializedName("word")
    val word: String
)