package com.inzynierkaApp.app.sample.ui.news

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.news.NewsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.news.mapStwToBr
import com.inzynierkaApp.app.sample.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment :
    BaseFragment<FragmentNewsBinding, NewsViewState, NewsViewEvent,
            NewsViewModel>(FragmentNewsBinding::inflate)
 {

    override val viewModel: NewsViewModel by viewModels()

    private val newsContentAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onViewEvent(NewsViewEvent.OnInitData)
    }

    override fun handleViewState(viewState: NewsViewState) {
        binding.apply {
            errorState.isVisible = false
            loadingState.isVisible = false
        }
        initContentContainer()
        when (viewState) {
            is NewsViewState.Success -> updateNewsList(viewState.news)
            is NewsViewState.Failure -> setErrorState()
            NewsViewState.Loading -> setLoadingState()
        }
    }

    private fun updateNewsList(newsList: NewsEntity) {
        newsContentAdapter.setData(newsList.data.br.motds.plus(
            mapStwToBr(newsList.data.stw?.messages ?: emptyList())
        ))
    }

    override fun onDestroyView() {
        binding.contentContainer.adapter = null
        super.onDestroyView()
    }

    private fun initContentContainer() {
        binding.contentContainer.setHasFixedSize(true)
        binding.contentContainer.adapter = newsContentAdapter
    }

    private fun setLoadingState() {
        newsContentAdapter.clear()
        binding.loadingState.isVisible = true
    }

    private fun setErrorState() {
        binding.errorState.isVisible = true
        newsContentAdapter.clear()
    }
}