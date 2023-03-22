package com.inzynierkaApp.app.sample.ui.map

import androidx.lifecycle.viewModelScope
import com.inzynierkaApp.app.core.base.BaseViewModel
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.mapwithpoints.MapWithPointsDetailsEntity
import com.inzynierkaApp.app.domain.usecases.GetMapWithPointsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private var getMapWithPointsUseCase: GetMapWithPointsUseCase
) : BaseViewModel<MapViewEvent, MapViewState>() {

    override fun onViewEvent(viewEvent: MapViewEvent) {
        when(viewEvent) {
            is MapViewEvent.OnInitData -> fetchMap()
        }
    }

    private fun fetchMap(){
        if (viewState.value is MapViewState.Success) return
        setLoadingState()

        viewModelScope.launch {
            getMapWithPointsUseCase
                .execute()
                .onSuccess { handleNetworkSuccess(it.data)}
                .onFailure {Timber.d("API CALL FAILED $it")}
        }
    }

    private fun handleNetworkSuccess(map: MapWithPointsDetailsEntity) =
        mutableViewState.postValue(MapViewState.Success(map))

    private fun setLoadingState() = mutableViewState.postValue(MapViewState.Loading)
}