package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.SurfingActivity

class GuideActivity : AppCompatActivity() {

    var back: ImageView? = null
    var dailyRoutine: Button? = null
    var faceExpressions: Button? = null
    var autism: Button? = null
    var behaviors: Button? = null
    var learningDelay: Button? = null
    var speechDelay: Button? = null
    var odd: Button? = null
    var adhd: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        supportActionBar?.hide()

        back = findViewById(R.id.close)
        dailyRoutine = findViewById(R.id.daily_routine)
        faceExpressions = findViewById(R.id.face_expression)
        autism = findViewById(R.id.autism)
        behaviors = findViewById(R.id.behaviors)
        learningDelay = findViewById(R.id.learning_delay)
        speechDelay = findViewById(R.id.speech_delay)
        odd = findViewById(R.id.odd)
        adhd = findViewById(R.id.adhd)

        back?.setOnClickListener {
            onBackPressed()
        }

        dailyRoutine?.setOnClickListener {
            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra("categoryPosition", 20)
            startActivity(intent)
        }

        faceExpressions?.setOnClickListener {
            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra("categoryPosition", 21)
            startActivity(intent)
        }
        autism?.setOnClickListener {
            startActivity(Intent(this,SurfingActivity::class.java).putExtra("fileName","autism.html"))
        }
        behaviors?.setOnClickListener {
            startActivity(Intent(this,SurfingActivity::class.java).putExtra("fileName","behaviors.html"))
        }
        learningDelay?.setOnClickListener {
            startActivity(Intent(this,SurfingActivity::class.java).putExtra("fileName","learning delay.html"))
        }
        speechDelay?.setOnClickListener {
            startActivity(Intent(this,SurfingActivity::class.java).putExtra("fileName","speechdelay.html"))
        }
        odd?.setOnClickListener {
            startActivity(Intent(this,SurfingActivity::class.java).putExtra("fileName","ODD.html"))
        }
        adhd?.setOnClickListener {
            startActivity(Intent(this,SurfingActivity::class.java).putExtra("fileName","tou.html"))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_down_a,R.anim.slide_down_b)
    }
}