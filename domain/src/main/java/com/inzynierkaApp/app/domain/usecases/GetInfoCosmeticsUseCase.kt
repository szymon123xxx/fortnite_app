package com.inzynierkaApp.app.domain.usecases

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.infoCosmetics.InfoCosmeticsEntity
import com.inzynierkaApp.app.domain.repositories.ContentRepository
import javax.inject.Inject

class GetInfoCosmeticsUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun execute(id: String): Result<InfoCosmeticsEntity> {
        return contentRepository.getInfoCosmetics(id)
    }
}