package com.inzynierkaApp.app.sample.ui.cosmetics

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class PrimaryViewEvent : BaseViewEvent {
    object OnInitData : PrimaryViewEvent()
}