package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model


import com.google.gson.annotations.SerializedName

data class IdentifiedMistake(
    @SerializedName("category")
    val category: String,
    @SerializedName("context")
    val context: String,
    @SerializedName("errorLength")
    val errorLength: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("offsetInContext")
    val offsetInContext: Int,
    @SerializedName("replacements")
    val replacements: List<String>,
    @SerializedName("ruleId")
    val ruleId: String,
    @SerializedName("ruleIssueType")
    val ruleIssueType: String,
    @SerializedName("sentence")
    val sentence: String
)