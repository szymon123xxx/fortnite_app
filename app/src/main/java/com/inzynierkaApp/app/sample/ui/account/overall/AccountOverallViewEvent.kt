package com.inzynierkaApp.app.sample.ui.account.overall

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class AccountOverallViewEvent : BaseViewEvent {
    data class OnInitData(val accountName: String)  : AccountOverallViewEvent()
}