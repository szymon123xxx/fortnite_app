package com.inzynierkaApp.app.sample.ui.map

import com.inzynierkaApp.app.core.base.BaseViewState
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.mapwithpoints.MapWithPointsDetailsEntity

sealed class MapViewState : BaseViewState {
    object Loading: MapViewState()
    data class Success(
        val map: MapWithPointsDetailsEntity
    ) : MapViewState()

    data class Failure(val reason: String) : MapViewState()
}