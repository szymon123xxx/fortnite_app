package com.inzynierkaApp.app.sample.ui.news

import com.inzynierkaApp.app.core.base.BaseViewEvent

sealed class NewsViewEvent : BaseViewEvent {
    object OnInitData : NewsViewEvent()
}