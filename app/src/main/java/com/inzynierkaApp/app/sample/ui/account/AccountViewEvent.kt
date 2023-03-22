package com.inzynierkaApp.app.sample.ui.account

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class AccountViewEvent : BaseViewEvent {
    data class OnInitData(val accountName: String) : AccountViewEvent()
}