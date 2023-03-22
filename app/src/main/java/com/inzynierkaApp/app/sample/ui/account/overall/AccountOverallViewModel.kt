package com.inzynierkaApp.app.sample.ui.account.overall

import androidx.lifecycle.viewModelScope
import com.inzynierkaApp.app.core.base.BaseViewModel
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.account.AccountInfoEntity
import com.inzynierkaApp.app.domain.usecases.GeyAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountOverallViewModel @Inject constructor(
    private var getAccountUseCase: GeyAccountUseCase,
) : BaseViewModel<AccountOverallViewEvent, AccountOverallViewState>() {

    override fun onViewEvent(viewEvent: AccountOverallViewEvent) {
        when (viewEvent) {
            is AccountOverallViewEvent.OnInitData -> fetchNewCosmetics(viewEvent.accountName)
        }
    }

    private fun fetchNewCosmetics(name: String) {
        if (viewState.value is AccountOverallViewState.Success) return
        setLoadingState()
        viewModelScope.launch {
            getAccountUseCase
                .execute(name)
                .onSuccess { handleNetworkSuccess(it) }
                .onFailure { handleError(it) }
        }
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mutableViewState.postValue(AccountOverallViewState.Failure("$error"))
    }

    private fun handleNetworkSuccess(accountInfo: AccountInfoEntity) =
        mutableViewState.postValue(AccountOverallViewState.Success(accountInfo))

    private fun setLoadingState() = mutableViewState.postValue(AccountOverallViewState.Loading)
}