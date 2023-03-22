package com.example.data.response.playlistinfo

import com.example.domain.entities.PlaylistInfoDetailsEntity
import com.example.domain.entities.PlaylistInfoImagesEntity
import com.google.gson.annotations.SerializedName

data class PlaylistInfoDetails (
    val id: String,
    var count: Int,
    val name: String?,
    @SerializedName("added")
    val date: String,
    val images: PlaylistInfoImages

) {
    fun toEntity(): PlaylistInfoDetailsEntity {
        return PlaylistInfoDetailsEntity(id, count, name, date, images.toEntity())
    }
}

data class PlaylistInfoImages(
    val showcase: String?,
    val missionIcon: String?
) {
    fun toEntity(): PlaylistInfoImagesEntity {
        return PlaylistInfoImagesEntity(showcase, missionIcon)
    }
}