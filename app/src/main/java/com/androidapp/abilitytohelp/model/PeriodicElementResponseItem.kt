package com.androidapp.abilitytohelp.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PeriodicElementResponseItem(
    @SerializedName("alloys")
    val alloys: String,
    @SerializedName("atomicMass")
    val atomicMass: String,
    @SerializedName("atomicNumber")
    val atomicNumber: String,
    @SerializedName("atomicRadius")
    val atomicRadius: String,
    @SerializedName("block")
    val block: String,
    @SerializedName("boilingPoint")
    val boilingPoint: String,
    @SerializedName("bondingType")
    val bondingType: String,
    @SerializedName("cpkHexColor")
    val cpkHexColor: String,
    @SerializedName("crystalStructure")
    val crystalStructure: String,
    @SerializedName("density")
    val density: String,
    @SerializedName("electronAffinity")
    val electronAffinity: String,
    @SerializedName("electronegativity")
    val electronegativity: String,
    @SerializedName("electronicConfiguration")
    val electronicConfiguration: String,
    @SerializedName("facts")
    val facts: String,
    @SerializedName("group")
    val group: String,
    @SerializedName("groupBlock")
    val groupBlock: String,
    @SerializedName("history")
    val history: String,
    @SerializedName("ionRadius")
    val ionRadius: String,
    @SerializedName("ionizationEnergy")
    val ionizationEnergy: String,
    @SerializedName("isotopes")
    val isotopes: String,
    @SerializedName("magneticOrdering")
    val magneticOrdering: String,
    @SerializedName("meltingPoint")
    val meltingPoint: String,
    @SerializedName("minerals")
    val minerals: String,
    @SerializedName("molarHeatCapacity")
    val molarHeatCapacity: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("oxidationStates")
    val oxidationStates: String,
    @SerializedName("period")
    val period: String,
    @SerializedName("speedOfSound")
    val speedOfSound: String,
    @SerializedName("standardState")
    val standardState: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("vanDelWaalsRadius")
    val vanDelWaalsRadius: String,
    @SerializedName("yearDiscovered")
    val yearDiscovered: String
) : Serializable