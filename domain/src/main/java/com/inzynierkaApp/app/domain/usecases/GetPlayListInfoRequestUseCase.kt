package com.inzynierkaApp.app.domain.usecases

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.playlistinforequestentity.PlayListInfoRequestEntity
import com.inzynierkaApp.app.domain.repositories.ContentRepository
import javax.inject.Inject

class GetPlayListInfoRequestUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {

    suspend fun execute(id: String): Result<PlayListInfoRequestEntity> {
        return contentRepository.getPlaylistInfoRequest(id)
    }
}