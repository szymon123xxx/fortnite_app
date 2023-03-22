package com.inzynierkaApp.app.sample.ui.cosmetics.infocosmetics

import androidx.lifecycle.viewModelScope
import com.inzynierkaApp.app.core.base.BaseViewModel
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.infoCosmetics.InfoCosmeticsEntity
import com.inzynierkaApp.app.domain.usecases.GetInfoCosmeticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoCosmeticsViewModel @Inject constructor(
    private var getInfoCosmeticsUseCase: GetInfoCosmeticsUseCase,
) : BaseViewModel<InfoCosmeticsViewEvent, InfoCosmeticsViewState>() {

    override fun onViewEvent(viewEvent: InfoCosmeticsViewEvent) {
        when (viewEvent) {
            is InfoCosmeticsViewEvent.OnInitData -> fetchAllCosmetics(viewEvent.cosmeticId)
        }
    }

    private fun fetchAllCosmetics(cosmeticId: String) {
        if (viewState.value is InfoCosmeticsViewState.Success) return
        setLoadingState()
        viewModelScope.launch {
            getInfoCosmeticsUseCase
                .execute(cosmeticId)
                .onSuccess { handleNetworkSuccess(it)}
                .onFailure { handleError(it)}
        }
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mutableViewState.postValue(InfoCosmeticsViewState.Failure("$error"))
    }

    private fun handleNetworkSuccess(newCosmetics: InfoCosmeticsEntity) =
        mutableViewState.postValue(InfoCosmeticsViewState.Success(newCosmetics))

    private fun setLoadingState() = mutableViewState.postValue(InfoCosmeticsViewState.Loading)
}