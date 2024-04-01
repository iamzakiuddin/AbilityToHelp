package com.apps.abilitytohelp.kidslearning.kidseducation.preschool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView


class SurfingActivity : AppCompatActivity() {

    var htmlLoader: WebView? = null
    var back: ImageView? = null
    var fileName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surfing)
        supportActionBar?.hide()
        fileName = intent.getStringExtra("fileName") ?: ""
        htmlLoader = findViewById(R.id.explorer)
        back = findViewById(R.id.back)
        back?.setOnClickListener {
            finish()
        }
        if (!fileName.isNullOrEmpty())
            htmlLoader?.loadUrl("file:///android_asset/$fileName")
    }
}