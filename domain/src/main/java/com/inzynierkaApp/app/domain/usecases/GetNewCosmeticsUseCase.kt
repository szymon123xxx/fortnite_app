package com.inzynierkaApp.app.domain.usecases

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.NewCosmeticsEntity
import com.inzynierkaApp.app.domain.repositories.ContentRepository
import javax.inject.Inject

class GetNewCosmeticsUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun execute(): Result<NewCosmeticsEntity> {
        return contentRepository.getNewCosmetics()
    }
}