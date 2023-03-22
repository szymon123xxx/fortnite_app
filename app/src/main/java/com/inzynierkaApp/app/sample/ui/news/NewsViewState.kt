package com.inzynierkaApp.app.sample.ui.news

import com.inzynierkaApp.app.core.base.BaseViewState
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.news.NewsEntity

sealed class NewsViewState : BaseViewState {
    object Loading: NewsViewState()
    data class Success(val news: NewsEntity) : NewsViewState()

    data class Failure(val reason: String) : NewsViewState()
}