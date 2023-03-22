package com.inzynierkaApp.app.sample.ui.map

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.mapwithpoints.MapWithPointsDetailsEntity
import com.inzynierkaApp.app.sample.databinding.FragmentMapBinding
import com.inzynierkaApp.app.sample.ui.extensions.loadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment :
    BaseFragment<FragmentMapBinding, MapViewState, MapViewEvent, MapViewModel>(
        FragmentMapBinding::inflate
) {

    override val viewModel: MapViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onViewEvent(MapViewEvent.OnInitData)
    }

    override fun handleViewState(viewState: MapViewState) {
        binding.errorState.isVisible = false
        binding.loadingState.isVisible = false
        when (viewState) {
            is MapViewState.Success -> updateMap(viewState.map)
            is MapViewState.Failure -> setErrorState(viewState.reason)
            MapViewState.Loading -> setLoadingState()
        }
    }

    private fun updateMap(map: MapWithPointsDetailsEntity) {
        binding.apply {
            mapImage.loadFromUrl(map.images.map)
        }
    }

    private fun setLoadingState() {
        binding.loadingState.isVisible = true
    }

    private fun setErrorState(error: String) {
        binding.errorState.isVisible = true
        binding.errorState.text = error
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
    }
}