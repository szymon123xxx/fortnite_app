package com.inzynierkaApp.app.sample.ui.cosmetics

import com.inzynierkaApp.app.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrimaryViewModel @Inject constructor() : BaseViewModel<PrimaryViewEvent, PrimaryViewState>() {

    override fun onViewEvent(viewEvent: PrimaryViewEvent) =
        mutableViewState.postValue(PrimaryViewState.SetUp)
}