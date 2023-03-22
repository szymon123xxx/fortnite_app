package com.inzynierkaApp.app.domain.usecases

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.mapwithpoints.MapWithPointsEntity
import com.inzynierkaApp.app.domain.repositories.ContentRepository
import javax.inject.Inject

class GetMapWithPointsUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun execute(): Result<MapWithPointsEntity> {
        return contentRepository.getMapWithPoints()
    }
}