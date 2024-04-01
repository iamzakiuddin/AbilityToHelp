package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model


import com.google.gson.annotations.SerializedName

data class AbbreviationsResult(
    @SerializedName("category")
    val category: Any,
    @SerializedName("categoryname")
    val categoryname: Any,
    @SerializedName("definition")
    val definition: Any,
    @SerializedName("id")
    val id: Any,
    @SerializedName("term")
    val term: Any,
    @SerializedName("parentcategory")
    val parentcategory : Any,
    @SerializedName("parentcategoryname")
    val parentcategoryname : Any
)