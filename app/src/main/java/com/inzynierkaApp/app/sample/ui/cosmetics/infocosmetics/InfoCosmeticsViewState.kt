package com.inzynierkaApp.app.sample.ui.cosmetics.infocosmetics

import com.inzynierkaApp.app.core.base.BaseViewState
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.infoCosmetics.InfoCosmeticsEntity

sealed class InfoCosmeticsViewState : BaseViewState {
    object Loading: InfoCosmeticsViewState()
    data class Success(val cosmetics: InfoCosmeticsEntity) : InfoCosmeticsViewState()

    data class Failure(val reason: String) : InfoCosmeticsViewState()
}