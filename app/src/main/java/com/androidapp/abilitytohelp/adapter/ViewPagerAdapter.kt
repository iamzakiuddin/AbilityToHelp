package com.androidapp.abilitytohelp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.androidapp.abilitytohelp.activity.AntonymsFragment
import com.androidapp.abilitytohelp.activity.SynonymsFragment

private const val NUM_TABS = 2

class ViewPagerAdapter( private val antonymsList: ArrayList<String>,
                        private val synonymsList: ArrayList<String>, fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private var antonymsFragment: AntonymsFragment? = null
    private var synonymsFragment: SynonymsFragment? = null
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = AntonymsFragment.newInstance(antonymsList)
                antonymsFragment = fragment
                fragment
            }
            else -> {
                val fragment = SynonymsFragment.newInstance(synonymsList)
                synonymsFragment = fragment
                fragment
            }
        }
    }

    fun updateData(a: ArrayList<String>, s: ArrayList<String>){
        antonymsList.clear()
        synonymsList.clear()
        antonymsList.addAll(a)
        synonymsList.addAll(s)
        antonymsFragment?.notifyData()
        synonymsFragment?.notifyData()
    }
}