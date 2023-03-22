package com.inzynierkaApp.app.sample.ui.cosmetics.all

import com.inzynierkaApp.app.core.base.BaseViewState
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.allcosmetics.AllCosmeticsEntity

sealed class AllCosmeticsViewState : BaseViewState {
    object Loading: AllCosmeticsViewState()
    data class Success(val cosmetics: AllCosmeticsEntity) : AllCosmeticsViewState()

    data class Failure(val reason: String) : AllCosmeticsViewState()
}