package com.inzynierkaApp.app.sample.ui.account.overall

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.account.AccountInfoEntity
import com.inzynierkaApp.app.sample.R
import com.inzynierkaApp.app.sample.databinding.FragmentAccountOverallBinding
import com.inzynierkaApp.app.sample.ui.extensions.loadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountOverallFragment :
    BaseFragment<FragmentAccountOverallBinding, AccountOverallViewState, AccountOverallViewEvent,
            AccountOverallViewModel>(FragmentAccountOverallBinding::inflate)
{

    override val viewModel: AccountOverallViewModel by viewModels()

    private val AccountFragmentArgs: AccountOverallFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onViewEvent(AccountOverallViewEvent.OnInitData(AccountFragmentArgs.userName))
    }

    override fun handleViewState(viewState: AccountOverallViewState) {
        hideInterface()
        binding.apply {
            errorState.isVisible = false
            loadingState.isVisible = false
        }
        when (viewState) {
            is AccountOverallViewState.Success -> updateAccountList(viewState.accountInfo)
            is AccountOverallViewState.Failure -> setErrorState()
            AccountOverallViewState.Loading -> setLoadingState()
        }
    }

    private fun updateAccountList(accountInfo: AccountInfoEntity) {
        binding.apply {
            shortInfoContainer.isVisible = true
            longInfoContainer.isVisible = true
            bombIcon.isVisible = true
            image.apply {
                if (accountInfo.data.image.isNullOrEmpty())
                    setImageResource(R.drawable.profile_placeholder)
                else
                    loadFromUrl(accountInfo.data.image)
            }
            level.text = accountInfo.data.battlePass.level.toString().plus(" ").plus("BP Lv")
            name.text = accountInfo.data.account.name
            kd.text = accountInfo.data.stats.all.overall.kd.toString().plus(" ").plus("K/D")
            kills.text = accountInfo.data.stats.all.overall.kills.toString().plus(" ").plus("Kills")
            wins.text = accountInfo.data.stats.all.overall.wins.toString()
            top3.text = accountInfo.data.stats.all.overall.top3.toString()
            minutesPlayed.text = accountInfo.data.stats.all.overall.minutesPlayed.toString()
            matches.text = accountInfo.data.stats.all.overall.matches.toString()
            killsPerMatch.text = accountInfo.data.stats.all.overall.killsPerMatch.toString()
            deaths.text = accountInfo.data.stats.all.overall.deaths.toString()
        }
    }

    private fun hideInterface() {
        binding.apply {
            shortInfoContainer.isVisible = false
            longInfoContainer.isVisible = false
        }
    }

    private fun setLoadingState() {
        binding.loadingState.isVisible = true
    }

    private fun setErrorState() {
        binding.errorState.isVisible = true
    }
}