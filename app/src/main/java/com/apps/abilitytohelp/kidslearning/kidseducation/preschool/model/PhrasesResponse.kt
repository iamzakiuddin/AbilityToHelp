package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model


import com.google.gson.annotations.SerializedName

data class PhrasesResponse(
    @SerializedName("result")
    val result: List<PhrasesResult>
)