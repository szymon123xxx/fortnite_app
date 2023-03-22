package com.inzynierkaApp.app.domain.usecases

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.allcosmetics.AllCosmeticsEntity
import com.inzynierkaApp.app.domain.repositories.ContentRepository
import javax.inject.Inject

class GetAllCosmeticsUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun execute(): Result<AllCosmeticsEntity> {
        return contentRepository.getALLCosmetics()
    }
}