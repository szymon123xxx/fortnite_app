package com.inzynierkaApp.app.sample.ui.home

import androidx.lifecycle.viewModelScope
import com.example.domain.entities.PlaylistInfoEntity
import com.inzynierkaApp.app.core.base.BaseViewModel
import com.inzynierkaApp.app.domain.usecases.GetPlaylistInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var getPlaylistInfoUseCase: GetPlaylistInfoUseCase
) : BaseViewModel<HomeViewEvent, HomeViewState>() {

    override fun onViewEvent(viewEvent: HomeViewEvent) {
        when (viewEvent) {
            is HomeViewEvent.OnInitData -> fetchFilmList()
        }
    }

    private fun fetchFilmList() {
        if (viewState.value is HomeViewState.Success) return
        setLoadingState()

        viewModelScope.launch {
            getPlaylistInfoUseCase
                .execute()
                .onSuccess { handleNetworkSuccess(it) }
                .onFailure(::handleError)
        }
    }

    private fun handleNetworkSuccess(modeList: PlaylistInfoEntity) =
        mutableViewState.postValue(HomeViewState.Success(modeList))

    private fun setLoadingState() = mutableViewState.postValue(HomeViewState.Loading)
}
