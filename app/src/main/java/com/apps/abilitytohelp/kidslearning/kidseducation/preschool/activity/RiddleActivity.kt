package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
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

class RiddleActivity : AppCompatActivity() {

    var showAnswer: Button? = null
    var riddle: TextView? = null
    var answer: TextView? = null
    var loading: ProgressBar? = null
    var back : LinearLayout? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null
    val viewmodel: FunActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle)
        supportActionBar?.hide()
        initUI()
        viewmodel.getRiddle()
        viewmodel.riddleResponseObserver().observe(this, Observer {
            when (it.status){
                NetworkResources.NetworkStatus.LOADING ->{
                    loading?.visibility = View.VISIBLE
                }
                NetworkResources.NetworkStatus.SUCCESS -> {
                    showAnswer?.isEnabled = true
                    loading?.visibility = View.GONE
                    riddle?.text = it.data?.riddle
                    answer?.text = it.data?.answer
                }
                NetworkResources.NetworkStatus.ERROR -> {
                    showAnswer?.isEnabled = true
                    loading?.visibility = View.GONE
                    Log.e("fuck",it.message?:"")
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                else ->{
                    showAnswer?.isEnabled = true
                    loading?.visibility = View.GONE
                    Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }
        })

        showAnswer?.setOnClickListener {
            answer?.visibility = View.VISIBLE
        }

        back?.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI() {
        showAnswer = findViewById(R.id.showAnswer)
        riddle = findViewById(R.id.riddle)
        answer = findViewById(R.id.answer)
        loading = findViewById(R.id.loading)
        back = findViewById(R.id.backBtn)
        var loadingDrawable = loading?.progressDrawable?.mutate()
        loadingDrawable?.setColorFilter(resources.getColor(R.color.yellow),android.graphics.PorterDuff.Mode.SRC_IN)
        showAnswer?.isEnabled = false
        loading?.progressDrawable = loadingDrawable
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left)
    }
}