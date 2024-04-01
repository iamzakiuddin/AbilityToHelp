package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.network.NetworkResources
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.viewmodels.FunActivityViewModel

class PartsOfSpeechActivity : AppCompatActivity() {

    val funActivityViewModel: FunActivityViewModel by viewModels()
    var loadingView: ProgressBar? = null
    var searchField: EditText? = null
    var searchBtn: Button? = null
    var back: LinearLayout? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null

    var wordLabel : TextView? = null
    var partOfSpeech : TextView? = null
    var verb : TextView? = null
    var adjective : TextView? = null
    var adverb : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parts_of_speech)

        supportActionBar?.hide()

        loadingView = findViewById(R.id.loading)
        wordLabel = findViewById(R.id.word)
        partOfSpeech = findViewById(R.id.partofspeech)
        verb = findViewById(R.id.verb)
        adjective = findViewById(R.id.adjective)
        adverb = findViewById(R.id.adverb)
        searchBtn = findViewById(R.id.searchBtn)
        searchField = findViewById(R.id.searchField)
        back = findViewById(R.id.backBtn)
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)

        back?.setOnClickListener {
            onBackPressed()
        }
        searchBtn?.setOnClickListener {
            var word = searchField?.text.toString()
            if (!word.isNullOrEmpty())
                funActivityViewModel.getPartsOfSpeech(word)
            hideKeyboardNow()
        }


        funActivityViewModel.partsOfSpeechObserver().observe(this, Observer {
            when (it.status){
                NetworkResources.NetworkStatus.LOADING -> {
                    loadingView?.visibility = View.VISIBLE
                }
                NetworkResources.NetworkStatus.SUCCESS-> {
                    loadingView?.visibility = View.GONE
                    wordLabel?.text = "WORD = ${it.data?.word}"
                    partOfSpeech?.text = "PART OF SPEECH = ${it.data?.partOfSpeech}"
                    verb?.text = "VERB = ${it.data?.otherPartOfSpeech?.verb}"
                    adjective?.text = "ADVERB = ${it.data?.otherPartOfSpeech?.adjective}"
                    adverb?.text = "ADVERB = ${it.data?.otherPartOfSpeech?.adverb}"

                }
                NetworkResources.NetworkStatus.ERROR -> {
                    loadingView?.visibility = View.GONE
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
                else ->{
                    loadingView?.visibility = View.GONE
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun hideKeyboardNow() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left)
    }
}