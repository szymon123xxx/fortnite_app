package com.inzynierkaApp.app.domain.usecases

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.account.AccountInfoEntity
import com.inzynierkaApp.app.domain.repositories.ContentRepository
import javax.inject.Inject

class GeyAccountUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {

    suspend fun execute(accountName: String): Result<AccountInfoEntity> {
        return contentRepository.getAccountInfo(accountName)
    }
}