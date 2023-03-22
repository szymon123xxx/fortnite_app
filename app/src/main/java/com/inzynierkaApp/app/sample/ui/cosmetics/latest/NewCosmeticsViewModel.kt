package com.inzynierkaApp.app.sample.ui.cosmetics.latest

import androidx.lifecycle.viewModelScope
import com.inzynierkaApp.app.core.base.BaseViewModel
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.NewCosmeticsEntity
import com.inzynierkaApp.app.domain.usecases.GetNewCosmeticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewCosmeticsViewModel @Inject constructor(
    private var getNewCosmeticsUseCase: GetNewCosmeticsUseCase,
) : BaseViewModel<NewCosmeticsViewEvent, NewCosmeticsViewState>() {

    override fun onViewEvent(viewEvent: NewCosmeticsViewEvent) {
        when (viewEvent) {
            is NewCosmeticsViewEvent.OnInitData -> fetchNewCosmetics()
        }
    }

    private fun fetchNewCosmetics() {
        if (viewState.value is NewCosmeticsViewState.Success) return
        setLoadingState()
        viewModelScope.launch {
            getNewCosmeticsUseCase
                .execute()
                .onSuccess { handleNetworkSuccess(it) }
                .onFailure { handleError(it) }
        }
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mutableViewState.postValue(NewCosmeticsViewState.Failure("$error"))
    }

    private fun handleNetworkSuccess(newCosmetics: NewCosmeticsEntity) =
        mutableViewState.postValue(NewCosmeticsViewState.Success(newCosmetics))

    private fun setLoadingState() = mutableViewState.postValue(NewCosmeticsViewState.Loading)
}