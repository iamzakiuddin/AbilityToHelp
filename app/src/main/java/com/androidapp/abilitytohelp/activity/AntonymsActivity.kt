package com.androidapp.abilitytohelp.activity

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
import androidx.viewpager2.widget.ViewPager2
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.adapter.ViewPagerAdapter
import com.androidapp.abilitytohelp.network.NetworkResources
import com.androidapp.abilitytohelp.utils.Utils
import com.androidapp.abilitytohelp.viewmodels.FunActivityViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AntonymsActivity : AppCompatActivity() {

    val funActivityViewModel: FunActivityViewModel by viewModels()
    var viewPager : ViewPager2? = null
    var tabLayout : TabLayout? = null
    var loadingView: ProgressBar? = null
    var searchField: EditText? = null
    var searchBtn: Button? = null
    var back: LinearLayout? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null
    val tabsArray = arrayOf("Antonyms","Synonyms")
    var antonymsList = ArrayList<String>()
    var synonymsList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antonyms)

        supportActionBar?.hide()
        loadingView = findViewById(R.id.loading)
        searchBtn = findViewById(R.id.searchBtn)
        searchField = findViewById(R.id.searchField)
        back = findViewById(R.id.backBtn)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
        val adapter = ViewPagerAdapter(antonymsList,synonymsList,supportFragmentManager, lifecycle)
        viewPager?.adapter = adapter
        if (tabLayout!=null && viewPager!=null){
            TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
                tab.text = tabsArray[position]
            }.attach()
        }

        back?.setOnClickListener {
            onBackPressed()
        }
        searchBtn?.setOnClickListener {
            var word = searchField?.text.toString()
            if (!word.isNullOrEmpty())
                funActivityViewModel.getAntonymSynonyms(word)
            hideKeyboardNow()
        }
        funActivityViewModel.antonymsSynResponse.observe(this, Observer {it->
            when (it.status){
                NetworkResources.NetworkStatus.LOADING -> {
                    loadingView?.visibility = View.VISIBLE
                }
                NetworkResources.NetworkStatus.SUCCESS-> {
                    loadingView?.visibility = View.GONE
                    adapter.updateData(it?.data?.antonyms as ArrayList<String>, it.data?.synonyms as ArrayList<String>)
                }
                NetworkResources.NetworkStatus.ERROR -> {
                    loadingView?.visibility = View.GONE
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                else ->{
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