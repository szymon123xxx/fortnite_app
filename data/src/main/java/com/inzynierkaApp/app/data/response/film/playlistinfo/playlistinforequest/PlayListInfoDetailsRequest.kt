package com.inzynierkaApp.app.data.response.film.playlistinfo.playlistinforequest

import com.example.data.response.playlistinfo.PlaylistInfoImages
import com.google.gson.annotations.SerializedName
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.playlistinforequestentity.PlaylistInfoDetailsRequestEntity

data class PlayListInfoDetailsRequest(
    val id: String,
    val name: String?,
    @SerializedName("added")
    val date: String?,
    val images: PlaylistInfoImages,
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
    val isLimitedTimeMode: Boolean?

) {
    fun toEntity(): PlaylistInfoDetailsRequestEntity {
        return PlaylistInfoDetailsRequestEntity(
            id,
            name,
            date,
            images.toEntity(),
            description,
            gameType,
            ratingType,
            minPlayers,
            maxPlayers,
            maxTeams,
            maxTeamSize,
            maxSquads,
            maxSquadSize,
            isTournament,
            isLimitedTimeMode
        )
    }
}