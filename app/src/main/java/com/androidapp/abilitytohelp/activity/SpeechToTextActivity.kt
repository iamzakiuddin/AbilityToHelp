package com.androidapp.abilitytohelp.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ComponentName
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.utils.Utils
import java.util.Locale


class SpeechToTextActivity : AppCompatActivity() {

    private var isComingFromSettings: Boolean = false
    val RecordAudioRequestCode = 1
    private var speechRecognizer: SpeechRecognizer? = null
    private var editText: EditText? = null
    private var micButton: ImageView? = null
    var back: LinearLayout? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_to_text)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission()
        }
        supportActionBar?.hide()


        editText = findViewById(R.id.text)
        micButton = findViewById(R.id.button)
        back = findViewById(R.id.backBtn)
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)

        back?.setOnClickListener {
            onBackPressed()
        }
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this, ComponentName(
            "com.google.android.tts",
            "com.google.android.apps.speech.tts.googletts.service.GoogleTTSRecognitionService"
        ))

        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);

        speechRecognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {}
            override fun onBeginningOfSpeech() {
                editText?.setText("")
                editText?.setHint("Listening...")
            }

            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(bytes: ByteArray) {}
            override fun onEndOfSpeech() {
                Log.e("End of speech","onEndOfSpeech")
            }
            override fun onError(i: Int) {
                editText?.setText("")
                micButton?.setImageResource(R.drawable.ic_mic_black_off)
            }
            override fun onResults(bundle: Bundle) {
                micButton?.setImageResource(R.drawable.ic_mic_black_off)
                val data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                editText?.setText(data!![0])
            }

            override fun onPartialResults(bundle: Bundle) {}
            override fun onEvent(i: Int, bundle: Bundle) {}
        })

        micButton?.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    speechRecognizer?.stopListening()
                }
                MotionEvent.ACTION_DOWN -> {
                    speechRecognizer?.let {
                        micButton?.setImageResource(R.drawable.ic_mic_black_24dp)
                        it.startListening(speechRecognizerIntent)
                    }
                }
            }
            false
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer?.destroy();
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), RecordAudioRequestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RecordAudioRequestCode && grantResults.isNotEmpty()){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                        showNotificationPermissionRationale()
                    } else {
                        showSettingDialog()
                    }
                }
            }else {
                Toast.makeText(this,"Audio permission granted for speech to text",Toast.LENGTH_SHORT).show()
            }
        }
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


    private fun showNotificationPermissionRationale() {
        AlertDialog.Builder(this)
            .setTitle(R.string.app_name)
            .setMessage("Ability to Help app needs permission to convert speech to text. If denied, this feature may not work properly. Thank you for understanding.")
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialogInterface, i -> checkPermission()})
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialogInterface, i -> finish()})
            .show()
    }

    private fun showSettingDialog() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage("Audio record permission is required, Please allow audio permission from setting")
            .setCancelable(false)
            .setPositiveButton(
                android.R.string.ok
            ) { dialogInterface, i ->
                isComingFromSettings = true
                openAppSettings()
            }
            .setNegativeButton(
                "Cancel"
            ) { dialogInterface, i -> finish() }
            .show()
    }

    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

}