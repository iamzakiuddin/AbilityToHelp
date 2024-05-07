package com.androidapp.abilitytohelp.activity.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.androidapp.abilitytohelp.R
import com.androidapp.abilitytohelp.adapter.AuthViewPagerAdapter
import com.androidapp.abilitytohelp.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AuthenticationActivity : AppCompatActivity() {

    var viewPager : ViewPager2? = null
    var tabLayout : TabLayout? = null
    val tabsArray = arrayOf("Sign up","Sign in")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        supportActionBar?.hide()
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        val adapter = AuthViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager?.adapter = adapter
        if (tabLayout!=null && viewPager!=null){
            TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
                tab.text = tabsArray[position]
            }.attach()
        }

    }
}