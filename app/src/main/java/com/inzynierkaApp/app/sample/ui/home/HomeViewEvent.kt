package com.inzynierkaApp.app.sample.ui.home

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class HomeViewEvent : BaseViewEvent {
    object OnInitData : HomeViewEvent()
}
