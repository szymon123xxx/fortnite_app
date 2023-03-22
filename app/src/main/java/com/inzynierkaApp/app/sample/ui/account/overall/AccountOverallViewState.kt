package com.inzynierkaApp.app.sample.ui.account.overall

import com.inzynierkaApp.app.core.base.BaseViewState
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.account.AccountInfoEntity

sealed class AccountOverallViewState : BaseViewState {
    object Loading: AccountOverallViewState()
    data class Success(val accountInfo: AccountInfoEntity) : AccountOverallViewState()
    data class Failure(val reason: String) : AccountOverallViewState()
}