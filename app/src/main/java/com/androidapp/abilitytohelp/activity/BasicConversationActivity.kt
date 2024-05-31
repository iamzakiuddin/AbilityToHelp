package com.androidapp.abilitytohelp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.OverlayItemAnimator
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.adapter.ConversationAdapter
import com.androidapp.abilitytohelp.interfaces.ConvoItemClick
import com.androidapp.abilitytohelp.model.ConvoResult
import com.androidapp.abilitytohelp.network.NetworkResources
import com.androidapp.abilitytohelp.viewmodels.FunActivityViewModel

class BasicConversationActivity : AppCompatActivity(), TextToSpeech.OnInitListener, ConvoItemClick {

    private var convoAdapter: ConversationAdapter? = null
    private var back: ImageView? = null
    private var conversationList: RecyclerView? = null
    val funActivityViewModel: FunActivityViewModel by viewModels()
    var basicConvoDataList = ArrayList<ConvoResult>()
    private var currentMessageIndex = 0
    private lateinit var textToSpeech: TextToSpeech
    private var loadingView: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_conversation)
        supportActionBar?.hide()
        textToSpeech = TextToSpeech(this, this)
        back = findViewById(R.id.close)
        loadingView = findViewById(R.id.loading)
        conversationList = findViewById(R.id.dataList)
        conversationList?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        convoAdapter = ConversationAdapter(basicConvoDataList,this)
        conversationList?.adapter = convoAdapter
        conversationList?.itemAnimator = OverlayItemAnimator()

        back?.setOnClickListener {
            onBackPressed()
        }

        textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {}

            override fun onDone(utteranceId: String?) {

            }

            override fun onError(utteranceId: String?) {}
        })

        funActivityViewModel.getBasicConversationList()
        funActivityViewModel.getBasicConversationListObserver().observe(this, Observer { it->
            when (it.status) {
                NetworkResources.NetworkStatus.LOADING -> {
                    loadingView?.visibility  = View.VISIBLE
                }

                NetworkResources.NetworkStatus.ERROR -> {
                    loadingView?.visibility  = View.GONE
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }

                NetworkResources.NetworkStatus.SUCCESS -> {
                    loadingView?.visibility  = View.GONE
                    basicConvoDataList.clear()
                    it.data?.results?.let { it1 -> basicConvoDataList.addAll(it1) }
                    convoAdapter?.notifyDataSetChanged()
                }
                else -> {
                    loadingView?.visibility  = View.GONE
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        })



    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            //playNextMessage()
        }
    }

    private fun playNextMessage() {
        if (currentMessageIndex < basicConvoDataList.size) {
            val message = basicConvoDataList[currentMessageIndex]
            currentMessageIndex++
            textToSpeech.speak(message.spanish+ "  " +message.english, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID)
        } else {
            convoAdapter?.updatePlayingPosition(-1) // Reset when done
        }
    }

    override fun onDestroy() {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }
        textToSpeech.shutdown()
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun hideKeyboardNow() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onItemClick(position: Int,currentSelectedPosition: Int) {
        val convoData = basicConvoDataList[position]
        if (currentSelectedPosition!=-1 && currentSelectedPosition!=position){
            basicConvoDataList[currentSelectedPosition].isSelected = false
        }
        convoData.isSelected = true
        convoAdapter?.currentPlayingPosition = position
        convoAdapter?.notifyDataSetChanged()
        textToSpeech.speak(convoData.spanish+ "  " +convoData.english, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID)
    }
}