package com.inzynierkaApp.app.data.response.film.playlistinfo.mapwithpoints

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.mapwithpoints.MapWithPointsEntity

data class MapWithPoints (
    val data: MapWithPointsDetails
){
    fun toEntity(): MapWithPointsEntity {
        return MapWithPointsEntity(data.toEntity())
    }
}