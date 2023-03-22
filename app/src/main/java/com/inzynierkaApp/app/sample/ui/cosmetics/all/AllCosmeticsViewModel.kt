package com.inzynierkaApp.app.sample.ui.cosmetics.all

import androidx.lifecycle.viewModelScope
import com.inzynierkaApp.app.core.base.BaseViewModel
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.allcosmetics.AllCosmeticsEntity
import com.inzynierkaApp.app.domain.usecases.GetAllCosmeticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCosmeticsViewModel @Inject constructor(
    private var getAllCosmeticsUseCase: GetAllCosmeticsUseCase,
) : BaseViewModel<AllCosmeticsViewEvent, AllCosmeticsViewState>() {

    override fun onViewEvent(viewEvent: AllCosmeticsViewEvent) {
        when (viewEvent) {
            is AllCosmeticsViewEvent.OnInitData -> fetchAllCosmetics()
        }
    }

    private fun fetchAllCosmetics() {
        if (viewState.value is AllCosmeticsViewState.Success) return
        setLoadingState()
        viewModelScope.launch {
            getAllCosmeticsUseCase
                .execute()
                .onSuccess { handleNetworkSuccess(it) }
                .onFailure { handleError(it) }
        }
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mutableViewState.postValue(AllCosmeticsViewState.Failure("$error"))
    }

    private fun handleNetworkSuccess(newCosmetics: AllCosmeticsEntity) =
        mutableViewState.postValue(AllCosmeticsViewState.Success(newCosmetics))

    private fun setLoadingState() = mutableViewState.postValue(AllCosmeticsViewState.Loading)
}