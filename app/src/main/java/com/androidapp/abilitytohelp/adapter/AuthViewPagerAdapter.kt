package com.androidapp.abilitytohelp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.androidapp.abilitytohelp.activity.profile.SignInFragment
import com.androidapp.abilitytohelp.activity.profile.SignUpFragment

private const val NUM_TABS = 2

class AuthViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private var signUpFragment: SignUpFragment? = null
    private var signInFragment: SignInFragment? = null
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = SignUpFragment()
                signUpFragment = fragment
                fragment
            }
            else -> {
                val fragment = SignInFragment()
                signInFragment = fragment
                fragment
            }
        }
    }

   /* fun updateData(a: ArrayList<String>, s: ArrayList<String>){
        antonymsList.clear()
        synonymsList.clear()
        antonymsList.addAll(a)
        synonymsList.addAll(s)
        antonymsFragment?.notifyData()
        synonymsFragment?.notifyData()
    }*/
}