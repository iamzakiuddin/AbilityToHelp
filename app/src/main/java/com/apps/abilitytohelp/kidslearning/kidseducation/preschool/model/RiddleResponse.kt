package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model


import com.google.gson.annotations.SerializedName

data class RiddleResponse(
    @SerializedName("answer")
    val answer: String,
    @SerializedName("difficultyLevel")
    val difficultyLevel: String,
    @SerializedName("postedBy")
    val postedBy: String,
    @SerializedName("riddle")
    val riddle: String,
    @SerializedName("upVotes")
    val upVotes: Int
)