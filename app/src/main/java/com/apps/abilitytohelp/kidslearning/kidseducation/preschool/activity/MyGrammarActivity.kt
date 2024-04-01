package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils

class MyGrammarActivity : AppCompatActivity() {

    var back : ImageView? = null
    var synonyms: Button? = null
    var antonymsSynoyms: Button? = null
    var speechFinder: Button? = null
    var grammarGenius: Button? = null
    var phrases: Button? = null
    var literature: Button? = null
    var dictionary: Button? = null
    var englishWords: Button? = null
    var abbreviations: Button? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_grammar)

        supportActionBar?.hide()
        initUI()
        back?.setOnClickListener {
            onBackPressed()
        }
        synonyms?.setOnClickListener {
            startActivity(Intent(this,SynonymsActivity::class.java))
            overridePendingTransition(R.anim.enter_anim,R.anim.exit)
        }

        antonymsSynoyms?.setOnClickListener {
            startActivity(Intent(this,AntonymsActivity::class.java))
            overridePendingTransition(R.anim.enter_anim,R.anim.exit)
        }

        speechFinder?.setOnClickListener {
            startActivity(Intent(this,PartsOfSpeechActivity::class.java))
            overridePendingTransition(R.anim.enter_anim,R.anim.exit)
        }

        grammarGenius?.setOnClickListener {
            startActivity(Intent(this,GrammarGeniusActivity::class.java))
            overridePendingTransition(R.anim.enter_anim,R.anim.exit)
        }

        phrases?.setOnClickListener {
            startActivity(Intent(this,PhrasesActivity::class.java))
            overridePendingTransition(R.anim.enter_anim,R.anim.exit)
        }

        literature?.setOnClickListener {
            startActivity(Intent(this,LiteratureActivity::class.java))
            overridePendingTransition(R.anim.enter_anim,R.anim.exit)
        }

        dictionary?.setOnClickListener {
            startActivity(Intent(this,DictionaryActivity::class.java))
            overridePendingTransition(R.anim.enter_anim,R.anim.exit)
        }

        englishWords?.setOnClickListener {
            startActivity(Intent(this,EnglishWords::class.java))
            overridePendingTransition(R.anim.enter_anim,R.anim.exit)
        }

        abbreviations?.setOnClickListener {
            startActivity(Intent(this,AbbreviationsActivity::class.java))
            overridePendingTransition(R.anim.enter_anim,R.anim.exit)
        }

    }

    private fun initUI() {
        back = findViewById(R.id.close)
        synonyms = findViewById(R.id.synonyms)
        antonymsSynoyms = findViewById(R.id.antonyms_synonyms)
        speechFinder = findViewById(R.id.speech_finder)
        grammarGenius = findViewById(R.id.grammar_genius)

        phrases = findViewById(R.id.phrases)
        literature = findViewById(R.id.literature)
        dictionary = findViewById(R.id.dictionary)
        englishWords = findViewById(R.id.english_words)
        abbreviations = findViewById(R.id.abbreviations)

        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_down_a,R.anim.slide_down_b)
    }
}