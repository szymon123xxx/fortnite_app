package com.inzynierkaApp.app.sample.ui.detail

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class DetailViewEvent : BaseViewEvent {
    data class OnInitData(val modeId: String) : DetailViewEvent()
}
