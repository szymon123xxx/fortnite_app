package com.inzynierkaApp.app.sample.ui.home

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.entities.PlaylistInfoEntity
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.sample.R
import com.inzynierkaApp.app.sample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewState, HomeViewEvent, HomeViewModel>(
        FragmentHomeBinding::inflate
    ),
    CityAdapterItemListener {

    override val viewModel: HomeViewModel by viewModels()

    private val contentAdapter = ContentAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentContainer()
        binding.toolbar.inflateMenu(R.menu.item_search)
        val searchView = binding.toolbar.menu.findItem(R.id.search_action).actionView as SearchView
        searchView.queryHint = "Search for game mode"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                contentAdapter.onTextChange(newText)
                return true
            }
        })
        binding.toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.option_1 -> contentAdapter.sortData(0)
                    R.id.option_2 -> contentAdapter.sortData(1)
                    R.id.option_3 -> contentAdapter.sortData(2)
                }
                true
        }
        viewModel.onViewEvent(HomeViewEvent.OnInitData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun handleViewState(viewState: HomeViewState) {
        binding.errorState.isVisible = false
        binding.loadingState.isVisible = false
        when (viewState) {
            is HomeViewState.Success -> updateFilmList(viewState.modeList)
            is HomeViewState.Failure -> setErrorState()
            HomeViewState.Loading -> setLoadingState()
        }
    }

    private fun updateFilmList(modeList: PlaylistInfoEntity) = contentAdapter.setData(modeList.data)

    override fun onDestroyView() {
        binding.contentContainer.adapter = null
        super.onDestroyView()
    }

    private fun initContentContainer() {
        binding.contentContainer.setHasFixedSize(true)
        binding.contentContainer.adapter = contentAdapter
    }

    private fun setLoadingState() {
        contentAdapter.clear()
        binding.loadingState.isVisible = true
    }

    private fun setErrorState() {
        binding.errorState.isVisible = true
        contentAdapter.clear()
    }

    override fun onItemClicked(modeId: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionFirstFragmentToDetailFragment(
                modeId
            )
        )
    }
}
