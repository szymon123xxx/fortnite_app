package com.inzynierkaApp.app.sample.ui.cosmetics

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.sample.R
import com.inzynierkaApp.app.sample.databinding.FragmentPrimaryBinding
import com.inzynierkaApp.app.sample.ui.cosmetics.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrimaryFragment :
    BaseFragment<FragmentPrimaryBinding, PrimaryViewState, PrimaryViewEvent,
            PrimaryViewModel>(FragmentPrimaryBinding::inflate) {

    override val viewModel: PrimaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onViewEvent(PrimaryViewEvent.OnInitData)
    }

    override fun handleViewState(viewState: PrimaryViewState) {
        when (viewState) {
            PrimaryViewState.SetUp -> setUpTabs()
        }
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            when(position){
                0 -> {
                    tab.text = "ALL"
                    tab.setIcon(R.drawable.ic_all_cosmetics)
                }
                1 -> {
                    tab.text = "NEW"
                    tab.setIcon(R.drawable.ic_new_cosmetics)
                }
            }
        }.attach()
    }
}