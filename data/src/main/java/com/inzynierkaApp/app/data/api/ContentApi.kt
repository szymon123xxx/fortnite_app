package com.inzynierkaApp.app.data.api

import com.example.data.response.playlistinfo.PlaylistInfo
import com.inzynierkaApp.app.data.response.film.playlistinfo.account.AccountInfo
import com.inzynierkaApp.app.data.response.film.playlistinfo.allcosmetics.AllCosmetics
import com.inzynierkaApp.app.data.response.film.playlistinfo.infocosmetics.InfoCosmetics
import com.inzynierkaApp.app.data.response.film.playlistinfo.mapwithpoints.MapWithPoints
import com.inzynierkaApp.app.data.response.film.playlistinfo.newcosmetics.NewCosmetics
import com.inzynierkaApp.app.data.response.film.playlistinfo.news.News
import com.inzynierkaApp.app.data.response.film.playlistinfo.playlistinforequest.PlaylistInfoRequest
import retrofit2.http.*

interface ContentApi {
    @GET("v1/playlists")
    suspend fun getPlaylistInfo(): PlaylistInfo

    @GET("v1/playlists/{playlist-id}")
    suspend fun getPlaylistInfoRequest(@Path(value = "playlist-id") id: String): PlaylistInfoRequest

    @GET("v1/map")
    suspend fun getMapWithPoints(): MapWithPoints

    @GET("v2/cosmetics/br/new")
    suspend fun getNewCosmetics(): NewCosmetics

    @GET("v2/cosmetics/br")
    suspend fun getALLCosmetics(): AllCosmetics

    @GET("v2/cosmetics/br/{cosmetic-id}")
    suspend fun getInfoCosmetics(@Path(value = "cosmetic-id") id: String): InfoCosmetics

    @GET("v2/news")
    suspend fun getNews(): News

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("v2/stats/br/v2")
    suspend fun getAccountInfo(
        @Header("Authorization") auth: String,
        @Query("name") name: String
    ): AccountInfo
}
