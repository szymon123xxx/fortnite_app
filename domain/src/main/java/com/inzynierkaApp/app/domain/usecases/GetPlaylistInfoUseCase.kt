package com.inzynierkaApp.app.domain.usecases

import com.example.domain.entities.PlaylistInfoEntity
import com.inzynierkaApp.app.domain.repositories.ContentRepository

import javax.inject.Inject

class GetPlaylistInfoUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {

    suspend fun execute(): Result<PlaylistInfoEntity> {
        return contentRepository.getPlaylistInfo()
    }
}