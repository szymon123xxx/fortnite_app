package com.inzynierkaApp.app.sample.ui.news

import androidx.lifecycle.viewModelScope
import com.inzynierkaApp.app.core.base.BaseViewModel
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.news.NewsEntity
import com.inzynierkaApp.app.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private var getNewsUseCase: GetNewsUseCase,
) : BaseViewModel<NewsViewEvent, NewsViewState>() {

    override fun onViewEvent(viewEvent: NewsViewEvent) {
        when (viewEvent) {
            is NewsViewEvent.OnInitData -> fetchAllNews()
        }
    }

    private fun fetchAllNews() {
        if (viewState.value is NewsViewState.Success) return
        setLoadingState()
        viewModelScope.launch {
            getNewsUseCase
                .execute()
                .onSuccess { handleNetworkSuccess(it) }
                .onFailure { handleError(it) }
        }
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mutableViewState.postValue(NewsViewState.Failure("$error"))
    }

    private fun handleNetworkSuccess(news: NewsEntity) =
        mutableViewState.postValue(NewsViewState.Success(news))

    private fun setLoadingState() = mutableViewState.postValue(NewsViewState.Loading)
}