package com.inzynierkaApp.app.data.response.film.playlistinfo.mapwithpoints

import com.google.gson.annotations.SerializedName
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.mapwithpoints.MapEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.mapwithpoints.MapWithPointsDetailsEntity

data class MapWithPointsDetails(
    val images: Map
){
    fun toEntity(): MapWithPointsDetailsEntity {
        return MapWithPointsDetailsEntity(images.toEntity())
    }
}

data class Map(
    @SerializedName("pois")
    val map: String
) {
    fun toEntity(): MapEntity {
        return MapEntity(map)
    }
}