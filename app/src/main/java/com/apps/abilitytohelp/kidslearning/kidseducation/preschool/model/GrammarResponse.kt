package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model


import com.google.gson.annotations.SerializedName

data class GrammarResponse(
    @SerializedName("identified_mistakes")
    val identifiedMistakes: List<IdentifiedMistake>
)