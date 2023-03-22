package com.inzynierkaApp.app.sample.ui.main

import androidx.lifecycle.SavedStateHandle
import com.inzynierkaApp.app.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @Suppress("UnusedPrivateMember") private val savedStateHandle: SavedStateHandle // keep if needed, if not, remove
) : BaseViewModel<MainViewEvent, MainViewState>() {

    @Suppress("EmptyFunctionBlock")
    override fun onViewEvent(viewEvent: MainViewEvent) { }
}
