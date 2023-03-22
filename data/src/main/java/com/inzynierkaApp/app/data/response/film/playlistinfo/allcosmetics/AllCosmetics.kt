package com.inzynierkaApp.app.data.response.film.playlistinfo.allcosmetics

import com.inzynierkaApp.app.data.response.film.playlistinfo.newcosmetics.ListOfNewItems
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.allcosmetics.AllCosmeticsEntity

data class AllCosmetics(val data: List<ListOfNewItems>) {
    fun toEntity(): AllCosmeticsEntity {
        return AllCosmeticsEntity(data.map{ it.toEntity() })
    }
}
