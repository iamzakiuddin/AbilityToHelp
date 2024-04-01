package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter.GrammarAdapter
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.GrammarResponse
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.IdentifiedMistake
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException


class GrammarGeniusActivity : AppCompatActivity() {

    var loadingView: ProgressBar? = null
    var searchField: EditText? = null
    var searchBtn: Button? = null
    var back: LinearLayout? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null
    var recyclerView: RecyclerView? = null
    var adapter: GrammarAdapter? = null
    var grammarResponseList = ArrayList<IdentifiedMistake>()

    val client = OkHttpClient()
    var requestBuilder: Request.Builder? = null
    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_genius)

        supportActionBar?.hide()
        loadingView = findViewById(R.id.loading)
        searchBtn = findViewById(R.id.searchBtn)
        searchField = findViewById(R.id.searchField)
        back = findViewById(R.id.backBtn)
        llAdView = findViewById(R.id.llAdView)
        recyclerView = findViewById(R.id.grammarErrorsListView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
        adapter = GrammarAdapter(grammarResponseList)
        recyclerView?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        recyclerView?.adapter = adapter
        back?.setOnClickListener {
            onBackPressed()
        }
        initNetworkSetup()

        searchBtn?.setOnClickListener {
            var word = searchField?.text.toString()
            if (!word.isNullOrEmpty()) {
                val body = RequestBody.create(mediaType, "query=$word")
                val instance = requestBuilder?.post(body)?.build()
                loadingView?.visibility = View.VISIBLE
                CoroutineScope(Dispatchers.IO).launch {
                    executeNetworkCall(instance)
                }
            }

            hideKeyboardNow()
        }
    }

    private fun initNetworkSetup() {
        requestBuilder = Request.Builder()
            .url("https://grammar-and-spellcheck.p.rapidapi.com/grammarandspellcheck")
            .addHeader("content-type", "application/x-www-form-urlencoded")
            .addHeader("X-RapidAPI-Key", "73c0c813a4mshebc31b8efca06b3p1dd4abjsn5b42d292097c")
            .addHeader("X-RapidAPI-Host", "grammar-and-spellcheck.p.rapidapi.com")
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


    private suspend fun executeNetworkCall(request: Request?) {
        try {
            withContext(Dispatchers.IO) {
                val response = request?.let { client.newCall(it).execute() }

                if (response != null && response.isSuccessful) {
                    val body = response.body
                    // Handle response
                    withContext(Dispatchers.Main) {
                        if (body != null) {
                            val gson = Gson()
                            val grammarResponse = gson.fromJson(body.string(), GrammarResponse::class.java)
                            if (grammarResponse != null && !grammarResponse.identifiedMistakes.isNullOrEmpty()) {
                                grammarResponseList.clear()
                                grammarResponseList.addAll(grammarResponse.identifiedMistakes)
                                adapter?.notifyDataSetChanged()
                                loadingView?.visibility = View.GONE
                            }
                        }
                    }
                } else {
                    // Handle unsuccessful response
                    withContext(Dispatchers.Main) {
                        loadingView?.visibility = View.GONE
                    }
                }
            }
        } catch (e: IOException) {
            // Handle IOException (e.g., no internet connection)
            withContext(Dispatchers.Main) {
                loadingView?.visibility = View.GONE
                Toast.makeText(this@GrammarGeniusActivity,e.localizedMessage,Toast.LENGTH_SHORT).show()
                // Show error message to the user
                // For example: Toast.makeText(context, "No internet connection. Please check your network settings.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            // Handle other exceptions
            withContext(Dispatchers.Main) {
                loadingView?.visibility = View.GONE
            }
        }
    }
}