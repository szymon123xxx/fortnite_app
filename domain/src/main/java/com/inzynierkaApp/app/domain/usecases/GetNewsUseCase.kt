package com.inzynierkaApp.app.domain.usecases

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.news.NewsEntity
import com.inzynierkaApp.app.domain.repositories.ContentRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun execute(): Result<NewsEntity> {
        return contentRepository.getNews()
    }
}