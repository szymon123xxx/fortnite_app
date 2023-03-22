package com.inzynierkaApp.app.sample.ui.detail

import com.inzynierkaApp.app.core.base.BaseViewState
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.playlistinforequestentity.PlaylistInfoDetailsRequestEntity

sealed class DetailViewState : BaseViewState {
    object Loading : DetailViewState()
    data class Success(
        val modeInfo: PlaylistInfoDetailsRequestEntity,
    ) : DetailViewState()

    data class Failure(val reason: String) : DetailViewState()
}
