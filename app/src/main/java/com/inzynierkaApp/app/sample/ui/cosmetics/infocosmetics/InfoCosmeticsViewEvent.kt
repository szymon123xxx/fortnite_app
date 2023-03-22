package com.inzynierkaApp.app.sample.ui.cosmetics.infocosmetics

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class InfoCosmeticsViewEvent : BaseViewEvent {
    data class OnInitData(val cosmeticId: String) : InfoCosmeticsViewEvent()
}