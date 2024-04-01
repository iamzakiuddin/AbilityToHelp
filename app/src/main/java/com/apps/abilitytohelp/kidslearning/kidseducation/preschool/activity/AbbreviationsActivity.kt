package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter.AbbreviationsAdapter
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter.DictionaryAdapter
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.AbbreviationsResult
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.model.DictonaryResult
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.network.NetworkResources
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.viewmodels.FunActivityViewModel

class AbbreviationsActivity : AppCompatActivity() {

    var loadingView: ProgressBar? = null
    var searchField: EditText? = null
    var searchBtn: Button? = null
    var back: LinearLayout? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null
    var recyclerView: RecyclerView? = null
    var adapter: AbbreviationsAdapter? = null
    var abbreviationsResultList = ArrayList<AbbreviationsResult>()
    val funActivityViewModel: FunActivityViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abbreviations)
        supportActionBar?.hide()
        loadingView = findViewById(R.id.loading)
        searchBtn = findViewById(R.id.searchBtn)
        searchField = findViewById(R.id.searchField)
        back = findViewById(R.id.backBtn)
        llAdView = findViewById(R.id.llAdView)
        recyclerView = findViewById(R.id.abbreviationsListView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
        adapter = AbbreviationsAdapter(abbreviationsResultList)
        recyclerView?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        recyclerView?.adapter = adapter

        back?.setOnClickListener {
            onBackPressed()
        }

        searchBtn?.setOnClickListener {
            var term = searchField?.text.toString()
            if (!term.isNullOrEmpty())
                funActivityViewModel.getAbbreviationsResponse(term)
            hideKeyboardNow()
        }

        funActivityViewModel.getAbbreviationsObserver().observe(this, Observer {it->
            when (it.status){
                NetworkResources.NetworkStatus.LOADING -> {
                    loadingView?.visibility = View.VISIBLE
                }
                NetworkResources.NetworkStatus.SUCCESS-> {
                    loadingView?.visibility = View.GONE
                    abbreviationsResultList.clear()
                    var dictionaryData = it?.data?.result as ArrayList
                    abbreviationsResultList.addAll(dictionaryData)
                    adapter?.notifyDataSetChanged()
                    recyclerView?.visibility = View.VISIBLE
                }
                NetworkResources.NetworkStatus.ERROR -> {
                    recyclerView?.visibility = View.GONE
                    loadingView?.visibility = View.GONE
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
                else ->{
                    recyclerView?.visibility = View.GONE
                    loadingView?.visibility = View.GONE
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        })
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
}