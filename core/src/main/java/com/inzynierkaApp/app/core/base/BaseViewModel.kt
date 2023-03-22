package com.inzynierkaApp.app.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

abstract class BaseViewModel<VE : BaseViewEvent, VS : BaseViewState> : ViewModel() {

    protected val mutableViewState = MutableLiveData<VS>()
    val viewState: LiveData<VS> = mutableViewState

    abstract fun onViewEvent(viewEvent: VE)

    protected open fun handleError(error: Throwable) {
        Timber.e(error)
    }
}
