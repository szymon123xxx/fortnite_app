package com.inzynierkaApp.app.sample.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.playlistinforequestentity.PlaylistInfoDetailsRequestEntity
import com.inzynierkaApp.app.sample.databinding.FragmentDetailBinding
import com.inzynierkaApp.app.sample.ui.extensions.loadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding, DetailViewState, DetailViewEvent, DetailViewModel>(
        FragmentDetailBinding::inflate
    ) {
    override val viewModel: DetailViewModel by viewModels()
    private val homeFragmentArgs: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewEvent(DetailViewEvent.OnInitData(homeFragmentArgs.modeId))
    }

    override fun handleViewState(viewState: DetailViewState) {
        binding.errorState.isVisible = false
        binding.loadingState.isVisible = false
        hideInterface()
        when (viewState) {
            is DetailViewState.Success -> setModeDetailPage(viewState.modeInfo)
            is DetailViewState.Failure -> setErrorState(viewState.reason)
            is DetailViewState.Loading -> setLoadingState()
        }
    }

    private fun setModeDetailPage(mode: PlaylistInfoDetailsRequestEntity) {

        binding.apply {
            detailScroll.isVisible = true
            name.text = mode.name
            mode.description?.let {
                linearLayout6.visibility = View.VISIBLE
                description.text = it
            }
            mode.gameType?.let {
                linearLayout4.visibility = View.VISIBLE
                gameType.text = it
            }
            mode.ratingType?.let {
                linearLayout5.visibility = View.VISIBLE
                ratingType.text = it
            }
            startDate.text = mode.date?.substring(0, 10).plus("  ").plus(
                mode.date?.substring(12, mode.date?.length?.minus(1) ?: 0)
            )
            minPlayers.text = mode.minPlayers.toString()
            maxPlayers.text = mode.maxPlayers.toString()
            maxTeams.text = mode.maxTeams.toString()
            maxTeamSize.text = mode.maxTeamSize.toString()
            maxSquads.text = mode.maxSquads.toString()
            maxSquadSize.text = mode.maxSquadSize.toString()
            if (mode.isTournament == true) isTournament.text = "Yes" else isTournament.text = "No"
            if (mode.isLimitedTimeMode == true) isLimitedTimeMode.text = "Yes" else isLimitedTimeMode.text = "No"
            image.loadFromUrl(mode.images.missionIcon ?: mode.images.showcase)
            if (minPlayers.text == "-1"){
                linearLayout7.isVisible = false
            } else if (maxPlayers.text == "-1") {
                linearLayout2.isVisible = false
            } else if (maxTeams.text == "-1") {
                linearLayout7.isVisible = false
            } else if (maxTeamSize.text == "-1") {
                linearLayout9.isVisible = false
            } else if (maxSquadSize.text == "-1") {
                linearLayout11.isVisible = false
            } else if (maxSquads.text == "-1") {
                linearLayout10.isVisible = false
            }
        }
    }

    private fun hideInterface() {
        binding.detailScroll.isVisible = false
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
