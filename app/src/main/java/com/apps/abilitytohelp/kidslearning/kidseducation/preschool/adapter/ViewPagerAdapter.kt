package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity.AntonymsFragment
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity.SynonymsFragment

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