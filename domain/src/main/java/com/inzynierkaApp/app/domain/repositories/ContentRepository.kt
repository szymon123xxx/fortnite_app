package com.inzynierkaApp.app.domain.repositories

import com.example.domain.entities.PlaylistInfoEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.account.AccountInfoEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.allcosmetics.AllCosmeticsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.infoCosmetics.InfoCosmeticsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.mapwithpoints.MapWithPointsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.NewCosmeticsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.news.NewsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.playlistinforequestentity.PlayListInfoRequestEntity

interface ContentRepository {

    suspend fun getPlaylistInfo(): Result<PlaylistInfoEntity>

    suspend fun getPlaylistInfoRequest(id: String): Result<PlayListInfoRequestEntity>

    suspend fun getMapWithPoints(): Result<MapWithPointsEntity>

    suspend fun getNewCosmetics(): Result<NewCosmeticsEntity>

    suspend fun getALLCosmetics(): Result<AllCosmeticsEntity>

    suspend fun getInfoCosmetics(id: String): Result<InfoCosmeticsEntity>

    suspend fun getNews(): Result<NewsEntity>

    suspend fun getAccountInfo(accountName: String): Result<AccountInfoEntity>
}
