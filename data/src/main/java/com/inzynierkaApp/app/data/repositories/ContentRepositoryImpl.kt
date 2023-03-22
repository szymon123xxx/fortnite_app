package com.inzynierkaApp.app.data.repositories

import com.example.domain.entities.PlaylistInfoEntity
import com.inzynierkaApp.app.data.api.ContentApi
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.account.AccountInfoEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.allcosmetics.AllCosmeticsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.infoCosmetics.InfoCosmeticsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.mapwithpoints.MapWithPointsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.NewCosmeticsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.news.NewsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.playlistinforequestentity.PlayListInfoRequestEntity
import com.inzynierkaApp.app.domain.repositories.ContentRepository

class ContentRepositoryImpl(
    private val contentApi: ContentApi
) : ContentRepository {

    override suspend fun getPlaylistInfo(): Result<PlaylistInfoEntity> {
        return kotlin.runCatching {
            contentApi.getPlaylistInfo()
        }.mapCatching {
            it.toEntity()
        }
    }

    override suspend fun getPlaylistInfoRequest(id: String): Result<PlayListInfoRequestEntity> {
        return kotlin.runCatching {
            contentApi.getPlaylistInfoRequest(id)
        }.mapCatching {
            it.toEntity()
        }
    }

    override suspend fun getMapWithPoints(): Result<MapWithPointsEntity> {
        return kotlin.runCatching {
            contentApi.getMapWithPoints()
        }.mapCatching {
            it.toEntity()
        }
    }

    override suspend fun getNewCosmetics(): Result<NewCosmeticsEntity> {
        return kotlin.runCatching {
            contentApi.getNewCosmetics()
        }.mapCatching {
            it.toEntity()
        }
    }

    override suspend fun getALLCosmetics(): Result<AllCosmeticsEntity> {
        return kotlin.runCatching {
            contentApi.getALLCosmetics()
        }.mapCatching {
            it.toEntity()
        }
    }

    override suspend fun getInfoCosmetics(id: String): Result<InfoCosmeticsEntity> {
        return kotlin.runCatching {
            contentApi.getInfoCosmetics(id)
        }.mapCatching {
            it.toEntity()
        }
    }

    override suspend fun getNews(): Result<NewsEntity> {
        return kotlin.runCatching {
            contentApi.getNews()
        }.mapCatching {
            it.toEntity()
        }
    }

    override suspend fun getAccountInfo(accountName: String): Result<AccountInfoEntity> {
        return kotlin.runCatching {
            contentApi.getAccountInfo("d5e06719-8186-4f72-bacc-409aae12c007", accountName)
        }.mapCatching {
            it.toEntity()
        }
    }
}
