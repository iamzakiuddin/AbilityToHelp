package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.PeriodicElementResponseItem
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils

class ElementDetail : AppCompatActivity() {

    var back : LinearLayout? = null
    var atomicMass : TextView? = null
    var atomicNumber : TextView? = null
    var atomicRadius : TextView? = null
    var block : TextView? = null
    var elementName : TextView? = null
    var bondingType : TextView? = null
    var crystalStructure : TextView? = null
    var density : TextView? = null
    var affinity : TextView? = null
    var negativity : TextView? = null
    var electronicConfiguration : TextView? = null
    var group : TextView? = null
    var groupBlock : TextView? = null
    var ionRadius : TextView? = null
    var ionizationEnergy : TextView? = null
    var isotopes : TextView? = null
    var magneticOrdering : TextView? = null
    var meltingPoints : TextView? = null
    var molarHeatCapacity : TextView? = null
    var oxidationStates : TextView? = null
    var period : TextView? = null
    var speedOfSound : TextView? = null
    var standardState : TextView? = null
    var vanDerWaalsRadius : TextView? = null
    var yearOfDiscovers : TextView? = null
    var minerals : TextView? = null
    var histroy : TextView? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_element_detail)
        initUI()
        back?.setOnClickListener {
            onBackPressed()
        }
        displayData()
    }

    @SuppressLint("SetTextI18n")
    private fun displayData() {
        val elementData = intent.getSerializableExtra("elementDetail") as? PeriodicElementResponseItem

        elementName?.text  = "${elementData?.name ?: ""} ${elementData?.symbol ?: ""}"
        atomicMass?.text = "Atomic mass: ${elementData?.atomicMass ?: ""}"
        atomicNumber?.text = "Atomic number: ${elementData?.atomicNumber?: ""}"
        atomicRadius?.text = "Atomic radius: ${elementData?.atomicRadius?: ""}"
        block?.text = "Block: ${elementData?.block}"
        bondingType?.text = "Bonding type: ${elementData?.bondingType?: ""}"
        crystalStructure?.text = "Crystal structure: ${elementData?.crystalStructure?: ""}"
        density?.text = "Density: ${elementData?.density?: ""}"
        affinity?.text = "Electron affinity: ${elementData?.electronAffinity?: ""}"
        negativity?.text = "Electron negativity: ${elementData?.electronegativity?: ""}"
        electronicConfiguration?.text = "Electronic configuration: ${elementData?.electronicConfiguration?: ""}"
        group?.text = "Group: ${elementData?.group?: ""}"
        groupBlock?.text = "Group block: ${elementData?.groupBlock?: ""}"
        ionRadius?.text = "Ion radius: ${elementData?.ionRadius?: ""}"
        ionizationEnergy?.text = "Ionization energy: ${elementData?.ionizationEnergy?: ""}"
        isotopes?.text = "Isotopes: ${elementData?.isotopes?: ""}"
        magneticOrdering?.text = "Magnetic ordering: ${elementData?.magneticOrdering?: ""}"
        meltingPoints?.text = "Melting point: ${elementData?.meltingPoint?: ""}"
        molarHeatCapacity?.text = "Molar heat capacity: ${elementData?.molarHeatCapacity?: ""}"
        oxidationStates?.text = "Oxidation states: ${elementData?.oxidationStates?: ""}"
        period?.text = "Period: ${elementData?.period?: ""}"
        speedOfSound?.text = "Speed of sound: ${elementData?.speedOfSound?: ""}"
        standardState?.text = "Standard state: ${elementData?.standardState?: ""}"
        vanDerWaalsRadius?.text = "van der Waals radius: ${elementData?.vanDelWaalsRadius?: ""}"
        yearOfDiscovers?.text = "Year of discover: ${elementData?.yearDiscovered?: ""}"
        minerals?.text = "Minerals: ${elementData?.minerals?: ""}"
        histroy?.text = "Histroy: ${elementData?.history?: ""}"
    }

    private fun initUI() {
        supportActionBar?.hide()
        back = findViewById(R.id.backBtn)
        atomicMass = findViewById(R.id.atomicMass)
        atomicNumber= findViewById(R.id.atomicNumber)
        atomicRadius = findViewById(R.id.atomicRadius)
        block = findViewById(R.id.block)
        elementName = findViewById(R.id.elementName)
        bondingType = findViewById(R.id.bondingType)
        crystalStructure = findViewById(R.id.crystalStructure)
        density = findViewById(R.id.density)
        affinity = findViewById(R.id.affinity)
        negativity = findViewById(R.id.negativity)
        electronicConfiguration = findViewById(R.id.electronicConfiguration)
        group = findViewById(R.id.group)
        groupBlock = findViewById(R.id.groupBlock)
        ionRadius = findViewById(R.id.ionRadius)
        ionizationEnergy = findViewById(R.id.ionizationEnergy)
        isotopes = findViewById(R.id.isotopes)
        magneticOrdering = findViewById(R.id.magneticOrdering)
        meltingPoints = findViewById(R.id.meltingPoints)
        molarHeatCapacity = findViewById(R.id.molarHeatCapacity)
        oxidationStates = findViewById(R.id.oxidationStates)
        period = findViewById(R.id.period)
        speedOfSound = findViewById(R.id.speedOfSound)
        standardState = findViewById(R.id.standardState)
        vanDerWaalsRadius = findViewById(R.id.vanDerWaalsRadius)
        yearOfDiscovers = findViewById(R.id.yearOfDiscovers)
        minerals = findViewById(R.id.minerals)
        histroy = findViewById(R.id.histroy)
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left)
    }
}
