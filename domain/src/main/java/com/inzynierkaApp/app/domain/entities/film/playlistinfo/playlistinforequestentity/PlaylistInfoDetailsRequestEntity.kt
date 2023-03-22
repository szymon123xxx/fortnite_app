package com.inzynierkaApp.app.domain.entities.film.playlistinfo.playlistinforequestentity

import com.example.domain.entities.PlaylistInfoImagesEntity

data class PlaylistInfoDetailsRequestEntity(
    val id: String,
    val name: String?,
    val date: String?,
    val images: PlaylistInfoImagesEntity,
    val description: String?,
    val gameType: String?,
    val ratingType: String?,
    val minPlayers: Int?,
    val maxPlayers: Int?,
    val maxTeams: Int?,
    val maxTeamSize: Int?,
    val maxSquads: Int?,
    val maxSquadSize: Int?,
    val isTournament: Boolean?,
    val isLimitedTimeMode: Boolean?,
)
