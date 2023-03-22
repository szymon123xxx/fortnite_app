package com.inzynierkaApp.app.sample.ui.account

import com.inzynierkaApp.app.core.base.BaseViewState
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.account.AccountInfoEntity

sealed class AccountViewState : BaseViewState {
    object Loading: AccountViewState()
    data class Success(val account: AccountInfoEntity) : AccountViewState()

    data class Failure(val reason: String) : AccountViewState()
}