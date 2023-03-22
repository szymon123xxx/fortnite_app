package com.inzynierkaApp.app.sample.ui.cosmetics

import com.inzynierkaApp.app.core.base.BaseViewState

sealed class PrimaryViewState : BaseViewState {
    object SetUp: PrimaryViewState()
}