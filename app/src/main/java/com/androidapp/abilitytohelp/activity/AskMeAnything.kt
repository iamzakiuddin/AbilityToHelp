package com.androidapp.abilitytohelp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.abilitytohelp.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.adapter.ChatAdapter
import com.androidapp.abilitytohelp.network.NetworkUtil
import com.androidapp.abilitytohelp.network.Repository
import com.androidapp.abilitytohelp.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AskMeAnything : AppCompatActivity() {
    var chatListView: RecyclerView? = null
    var inputField: EditText? = null
    var send: ImageView? = null
    var back : LinearLayout? = null
    var dataAdapter : ChatAdapter? = null
    var repo:Repository? = null
    var dataList = ArrayList<String>()
    var generativeModel : GenerativeModel? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null
    init {
        repo = NetworkUtil.provideRepository()
        generativeModel = GenerativeModel(
            // Use a model that's applicable for your use case (see "Implement basic use cases" below)
            modelName = "gemini-pro",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = BuildConfig.GEMINI_AI
        )
        dataAdapter = ChatAdapter(dataList)
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_me_anything)
        initUI()
        supportActionBar?.hide()



        back?.setOnClickListener {
            onBackPressed()
        }
        send?.setOnClickListener {
            val prompt = inputField?.text?.toString()
            if (prompt != null) {
                hideKeyboardNow()
                if (Utils.isNetworkConnected(this@AskMeAnything)){
                    dataList.add(0,"You : $prompt")
                    dataAdapter?.notifyDataSetChanged()
                    inputField?.text?.clear()

                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val response = generativeModel?.generateContent(prompt)
                            withContext(Dispatchers.Main){
                                response?.text?.let { it1 ->
                                    dataList.add(0,"AI: $it1")
                                    dataAdapter?.notifyDataSetChanged()
                                }
                            }
                        }catch (ex: Exception){
                            withContext(Dispatchers.Main){
                                Toast.makeText(this@AskMeAnything, ex.localizedMessage,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }else{
                    Toast.makeText(this,"No Internet connectivity!",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun initUI() {
        back = findViewById(R.id.backBtn)
        chatListView = findViewById(R.id.chatListView)
        send = findViewById(R.id.send)
        inputField = findViewById(R.id.searchField)
        chatListView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true)
        chatListView?.adapter = dataAdapter
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
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