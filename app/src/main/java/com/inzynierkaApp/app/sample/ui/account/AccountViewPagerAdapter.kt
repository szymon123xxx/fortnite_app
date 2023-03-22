package com.inzynierkaApp.app.sample.ui.account

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.inzynierkaApp.app.sample.ui.account.overall.AccountOverallFragment
import com.inzynierkaApp.app.sample.ui.cosmetics.PrimaryFragment
import com.inzynierkaApp.app.sample.ui.cosmetics.latest.NewCosmeticsFragment

private const val NUM_TABS = 2

class AccountViewPagerAdapter(supportFragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(supportFragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return AccountOverallFragment()
            1 -> return NewCosmeticsFragment()
        }
        return PrimaryFragment()
    }
}