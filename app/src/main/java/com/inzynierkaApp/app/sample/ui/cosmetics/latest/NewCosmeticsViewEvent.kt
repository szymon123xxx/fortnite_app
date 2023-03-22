package com.inzynierkaApp.app.sample.ui.cosmetics.latest

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class NewCosmeticsViewEvent : BaseViewEvent {
    object OnInitData : NewCosmeticsViewEvent()
}