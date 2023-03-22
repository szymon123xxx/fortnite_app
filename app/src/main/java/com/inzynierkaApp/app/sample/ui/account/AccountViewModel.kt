package com.inzynierkaApp.app.sample.ui.account

import androidx.lifecycle.viewModelScope
import com.inzynierkaApp.app.core.base.BaseViewModel
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.account.AccountInfoEntity
import com.inzynierkaApp.app.domain.usecases.GeyAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private var getAccountUseCase: GeyAccountUseCase,
) : BaseViewModel<AccountViewEvent, AccountViewState>() {

    override fun onViewEvent(viewEvent: AccountViewEvent) {
        when (viewEvent) {
            is AccountViewEvent.OnInitData -> fetchAllNews(viewEvent.accountName )
        }
    }

    private fun fetchAllNews(accountName: String) {
        if (viewState.value is AccountViewState.Success) return
        setLoadingState()
        viewModelScope.launch {
            getAccountUseCase
                .execute(accountName)
                .onSuccess { handleNetworkSuccess(it) }
                .onFailure { handleError(it) }
        }
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mutableViewState.postValue(AccountViewState.Failure("$error"))
    }

    private fun handleNetworkSuccess(account: AccountInfoEntity) =
        mutableViewState.postValue(AccountViewState.Success(account))

    private fun setLoadingState() = mutableViewState.postValue(AccountViewState.Loading)
}