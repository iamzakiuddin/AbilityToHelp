package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName

data class GrammarResponse(
    @SerializedName("identified_mistakes")
    val identifiedMistakes: List<IdentifiedMistake>
)