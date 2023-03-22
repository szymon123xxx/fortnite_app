package com.inzynierkaApp.app.sample.ui.cosmetics.all

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class AllCosmeticsViewEvent : BaseViewEvent {
    object OnInitData : AllCosmeticsViewEvent()
}