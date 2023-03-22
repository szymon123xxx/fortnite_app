package com.example.domain.entities

class PlaylistInfoDetailsEntity (
    val id: String,
    var count: Int,
    val name: String?,
    val date: String,
    val images: PlaylistInfoImagesEntity
)

data class PlaylistInfoImagesEntity(
    val showcase: String?,
    val missionIcon: String?
)