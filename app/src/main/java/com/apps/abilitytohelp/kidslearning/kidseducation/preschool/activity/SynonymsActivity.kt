package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter.SynonymAdapter
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.network.NetworkResources
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.viewmodels.FunActivityViewModel


class SynonymsActivity : AppCompatActivity() {

    val funActivityViewModel: FunActivityViewModel by viewModels()
    var loadingView: ProgressBar? = null
    var synonymsListView: RecyclerView? = null
    var searchField: EditText? = null
    var searchBtn: Button? = null
    var back: LinearLayout? = null
    var synonymsList = ArrayList<String>()
    var synonymAdapter : SynonymAdapter? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_synonyms)
        supportActionBar?.hide()

        loadingView = findViewById(R.id.loading)
        synonymsListView = findViewById(R.id.synonymsListView)
        searchBtn = findViewById(R.id.searchBtn)
        searchField = findViewById(R.id.searchField)
        back = findViewById(R.id.backBtn)
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
        synonymAdapter = SynonymAdapter(synonymsList)
        synonymsListView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        synonymsListView?.adapter = synonymAdapter

        back?.setOnClickListener {
            onBackPressed()
        }
        searchBtn?.setOnClickListener {
            var word = searchField?.text.toString()
            if (!word.isNullOrEmpty())
                funActivityViewModel.getSynonyms(word)
            hideKeyboardNow()
        }
        funActivityViewModel.synonymsResponseObserver().observe(this, Observer {it->
            when (it.status){
                NetworkResources.NetworkStatus.LOADING -> {
                    loadingView?.visibility = View.VISIBLE
                }
                NetworkResources.NetworkStatus.SUCCESS-> {
                    loadingView?.visibility = View.GONE
                    synonymsList.clear()
                    var synonymsData = it?.data?.synonyms as ArrayList
                    synonymsList.addAll(synonymsData)
                    synonymAdapter?.notifyDataSetChanged()
                    synonymsListView?.visibility = View.VISIBLE
                }
                NetworkResources.NetworkStatus.ERROR -> {
                    synonymsListView?.visibility = View.GONE
                    loadingView?.visibility = View.GONE
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                else ->{
                    synonymsListView?.visibility = View.GONE
                    loadingView?.visibility = View.GONE
                    Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
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