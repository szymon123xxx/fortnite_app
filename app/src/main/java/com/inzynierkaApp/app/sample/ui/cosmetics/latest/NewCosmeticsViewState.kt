package com.inzynierkaApp.app.sample.ui.cosmetics.latest

import com.inzynierkaApp.app.core.base.BaseViewState
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.NewCosmeticsEntity

sealed class NewCosmeticsViewState : BaseViewState {
    object Loading: NewCosmeticsViewState()
    data class Success(val newCosmetics: NewCosmeticsEntity) : NewCosmeticsViewState()
    data class Failure(val reason: String) : NewCosmeticsViewState()
}