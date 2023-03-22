package com.inzynierkaApp.app.sample.ui.cosmetics.infocosmetics

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.infoCosmetics.InfoCosmeticsEntity
import com.inzynierkaApp.app.sample.databinding.FragmentInfoCosmeticsBinding
import com.inzynierkaApp.app.sample.ui.extensions.loadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoCosmeticsFragment :
    BaseFragment<FragmentInfoCosmeticsBinding, InfoCosmeticsViewState, InfoCosmeticsViewEvent,
            InfoCosmeticsViewModel>(
        FragmentInfoCosmeticsBinding::inflate
    ) {
    override val viewModel: InfoCosmeticsViewModel by viewModels()

    private val homeFragmentArgs: InfoCosmeticsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewEvent(InfoCosmeticsViewEvent.OnInitData(homeFragmentArgs.allCosmeticId))
    }

    override fun handleViewState(viewState: InfoCosmeticsViewState) {
        binding.errorState.isVisible = false
        binding.loadingState.isVisible = false
        hideInterface()
        when (viewState) {
            is InfoCosmeticsViewState.Success -> setModeDetailPage(viewState.cosmetics)
            is InfoCosmeticsViewState.Failure -> setErrorState(viewState.reason)
            is InfoCosmeticsViewState.Loading -> setLoadingState()
        }
    }

    private fun setModeDetailPage(mode: InfoCosmeticsEntity) {

        binding.apply {
            detailScroll.isVisible = true
            name.text = mode.data.name
            rarity.text = mode.data.rarity?.displayValue
            if (mode.data.set == null && mode.data.introduction == null )
                linearLayout6.visibility = View.GONE
            description.text = mode.data.set?.text?.plus(" ")?.plus(mode.data.introduction?.text)
            date.text = mode.data.added?.substring(0, 10).plus("  ").plus(
                mode.data.added?.substring(12, mode.data.added?.length?.minus(1) ?: 0)
            )
            itemType.text = mode.data.type.rarity
            image.loadFromUrl(mode.data.images.icon ?: mode.data.images.smallIcon)
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