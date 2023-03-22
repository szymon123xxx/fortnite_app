package com.inzynierkaApp.app.sample.ui.detail

import androidx.lifecycle.viewModelScope
import com.inzynierkaApp.app.core.base.BaseViewModel
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.playlistinforequestentity.PlayListInfoRequestEntity
import com.inzynierkaApp.app.domain.usecases.GetPlayListInfoRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private var getModeInfoRequestUseCase: GetPlayListInfoRequestUseCase,
) : BaseViewModel<DetailViewEvent, DetailViewState>() {

    override fun onViewEvent(viewEvent: DetailViewEvent) {
        when (viewEvent) {
            is DetailViewEvent.OnInitData -> fetchInfoModeRequest(viewEvent.modeId)
        }
    }

    private fun fetchInfoModeRequest(modeId: String) {
        if (viewState.value is DetailViewState.Success) return
        setLoadingState()
        viewModelScope.launch {
            getModeInfoRequestUseCase
                .execute(modeId)
                .onSuccess { handleNetworkSuccess(it) }
                .onFailure { handleError(it) }
        }
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mutableViewState.postValue(DetailViewState.Failure("$error"))
    }

    private fun handleNetworkSuccess(modeInfo: PlayListInfoRequestEntity) =
        mutableViewState.postValue(DetailViewState.Success(modeInfo.data))

    private fun setLoadingState() = mutableViewState.postValue(DetailViewState.Loading)
}
