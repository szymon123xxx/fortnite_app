package com.inzynierkaApp.app.sample.ui.cosmetics.latest

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.NewCosmeticsEntity
import com.inzynierkaApp.app.sample.R
import com.inzynierkaApp.app.sample.databinding.FragmentNewCosmeticsBinding
import com.inzynierkaApp.app.sample.ui.cosmetics.PrimaryFragmentDirections
import com.inzynierkaApp.app.sample.ui.cosmetics.adapter.CosmeticsAdapterItemListener
import com.inzynierkaApp.app.sample.ui.cosmetics.adapter.NewCosmeticsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewCosmeticsFragment :
    BaseFragment<FragmentNewCosmeticsBinding, NewCosmeticsViewState, NewCosmeticsViewEvent,
            NewCosmeticsViewModel>(FragmentNewCosmeticsBinding::inflate),

    CosmeticsAdapterItemListener {

    override val viewModel: NewCosmeticsViewModel by viewModels()

    private val cosmeticsContentAdapter = NewCosmeticsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onViewEvent(NewCosmeticsViewEvent.OnInitData)
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

    override fun handleViewState(viewState: NewCosmeticsViewState) {
        binding.apply {
            errorState.isVisible = false
            loadingState.isVisible = false
        }
        initContentContainer()
        when (viewState) {
            is NewCosmeticsViewState.Success -> updateCosmeticsList(viewState.newCosmetics)
            is NewCosmeticsViewState.Failure -> setErrorState()
            NewCosmeticsViewState.Loading -> setLoadingState()
        }
    }

    private fun updateCosmeticsList(cosmeticsList: NewCosmeticsEntity) =
        cosmeticsContentAdapter.setData(cosmeticsList.data.items)

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

    override fun onItemClicked(cosmeticId: String) {
        findNavController().navigate(
            PrimaryFragmentDirections.actionPrimaryFragmentToInfoCosmeticsFragment(cosmeticId)
        )
    }
}