package com.example.data.response.playlistinfo

import com.example.domain.entities.PlaylistInfoEntity

data class PlaylistInfo (
    val status: Int,
    val data: List<PlaylistInfoDetails>
) {
    fun toEntity(): PlaylistInfoEntity {
        return PlaylistInfoEntity(status, data.map { it.toEntity() })
    }
}