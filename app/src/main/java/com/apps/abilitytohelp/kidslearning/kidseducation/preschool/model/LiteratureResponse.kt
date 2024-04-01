package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model


import com.google.gson.annotations.SerializedName

data class LiteratureResponse(
    @SerializedName("result")
    val result: List<LiteratureResult>
)