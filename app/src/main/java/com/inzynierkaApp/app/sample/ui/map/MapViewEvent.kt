package com.inzynierkaApp.app.sample.ui.map

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class MapViewEvent : BaseViewEvent {
    object OnInitData : MapViewEvent()
}