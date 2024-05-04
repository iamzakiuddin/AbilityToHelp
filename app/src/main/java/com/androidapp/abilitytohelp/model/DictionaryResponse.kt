package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class DictionaryResponse(
    @SerializedName("result")
    val result: List<DictonaryResult>
)