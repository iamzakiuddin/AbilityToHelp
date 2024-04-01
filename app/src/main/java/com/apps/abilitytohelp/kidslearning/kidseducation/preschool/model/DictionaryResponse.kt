package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model


import com.google.gson.annotations.SerializedName

data class DictionaryResponse(
    @SerializedName("result")
    val result: List<DictonaryResult>
)