package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model

import com.google.gson.annotations.SerializedName
data class WordImageResponse(
    @SerializedName("query")
    val query: String,
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("images")
    val images: List<String>,
)
