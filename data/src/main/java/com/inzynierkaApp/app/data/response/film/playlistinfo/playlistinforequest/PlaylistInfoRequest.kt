package com.inzynierkaApp.app.data.response.film.playlistinfo.playlistinforequest

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.playlistinforequestentity.PlayListInfoRequestEntity

data class PlaylistInfoRequest (
    val status: Int,
    val data: PlayListInfoDetailsRequest
) {
    fun toEntity(): PlayListInfoRequestEntity {
        return PlayListInfoRequestEntity(data.toEntity())
    }
}