package com.inzynierkaApp.app.sample.ui.home

import com.example.domain.entities.PlaylistInfoEntity
import com.inzynierkaApp.app.core.base.BaseViewState

sealed class HomeViewState : BaseViewState {
    object Loading : HomeViewState()
    data class Success(
        val modeList: PlaylistInfoEntity,
    ) : HomeViewState()

    data class Failure(val reason: String) : HomeViewState()
}
