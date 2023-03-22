package com.inzynierkaApp.app.sample.ui.cosmetics.all

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.allcosmetics.AllCosmeticsEntity
import com.inzynierkaApp.app.sample.R
import com.inzynierkaApp.app.sample.databinding.FragmentAllCosmeticsBinding
import com.inzynierkaApp.app.sample.ui.cosmetics.PrimaryFragmentDirections
import com.inzynierkaApp.app.sample.ui.cosmetics.adapter.AllCosmeticsAdapter
import com.inzynierkaApp.app.sample.ui.cosmetics.adapter.AllCosmeticsAdapterItemListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCosmeticsFragment :
    BaseFragment<FragmentAllCosmeticsBinding, AllCosmeticsViewState, AllCosmeticsViewEvent,
            AllCosmeticsViewModel>(FragmentAllCosmeticsBinding::inflate),
    AllCosmeticsAdapterItemListener {

    override val viewModel: AllCosmeticsViewModel by viewModels()

    private val cosmeticsContentAdapter = AllCosmeticsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onViewEvent(AllCosmeticsViewEvent.OnInitData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.inflateMenu(R.menu.item_search_single)
        val searchView = binding.toolbar.menu.findItem(R.id.search_action).actionView as SearchView
        searchView.queryHint = "Search cosmetics"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cosmeticsContentAdapter.onTextChange(newText)
                return true
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun handleViewState(viewState: AllCosmeticsViewState) {
        binding.apply {
            errorState.isVisible = false
            loadingState.isVisible = false
        }
        initContentContainer()
        when (viewState) {
            is AllCosmeticsViewState.Success -> updateCosmeticsList(viewState.cosmetics)
            is AllCosmeticsViewState.Failure -> setErrorState()
            AllCosmeticsViewState.Loading -> setLoadingState()
        }
    }

    private fun updateCosmeticsList(cosmeticsList: AllCosmeticsEntity) =
        cosmeticsContentAdapter.setData(cosmeticsList.data)

    override fun onDestroyView() {
        binding.contentContainer.adapter = null
        super.onDestroyView()
    }

    private fun initContentContainer() {
        binding.contentContainer.setHasFixedSize(true)
        binding.contentContainer.adapter = cosmeticsContentAdapter
    }

    private fun setLoadingState() {
        cosmeticsContentAdapter.clear()
        binding.loadingState.isVisible = true
    }

    private fun setErrorState() {
        binding.errorState.isVisible = true
        cosmeticsContentAdapter.clear()
    }

    override fun onItemClicked(cosmeticId: String) =
        findNavController().navigate(
            PrimaryFragmentDirections.actionPrimaryFragmentToInfoCosmeticsFragment(cosmeticId)
        )
}