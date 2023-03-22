package com.inzynierkaApp.app.sample.ui.account

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inzynierkaApp.app.core.base.BaseFragment
import com.inzynierkaApp.app.sample.R
import com.inzynierkaApp.app.sample.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment :
    BaseFragment<FragmentAccountBinding, AccountViewState, AccountViewEvent,
            AccountViewModel>(FragmentAccountBinding::inflate) {

    override val viewModel: AccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val highScore = sharedPref?.getString(
            resources.getString(
                R.string.shared_preferences_account_name
            ), ""
        )
        if (!highScore.isNullOrEmpty()) binding.textInputEditText.setText(highScore)

        binding.buttonCheck.setOnClickListener {
            viewModel.onViewEvent(AccountViewEvent.OnInitData(binding.textInputEditText.text.toString()))
        }
    }

    override fun handleViewState(viewState: AccountViewState) {
        binding.apply {
            errorState.isVisible = false
            loadingState.isVisible = false
        }

        when (viewState) {
            is AccountViewState.Success -> updateNewsList()
            is AccountViewState.Failure -> setErrorState()
            AccountViewState.Loading -> setLoadingState()
        }
    }

    private fun updateNewsList() {
        binding.apply {
            buttonCheck.isVisible = false
            textInputEditText.isVisible = false
        }
        saveName()
        findNavController().navigate(
            AccountFragmentDirections.actionAccountFragmentToAccountOverallFragment(
                binding.textInputEditText.text.toString()
            )
        )
    }

    private fun saveName() {
        val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref?.edit()) {
            this?.putString(
                getString(R.string.shared_preferences_account_name),
                binding.textInputEditText.text.toString()
            )
            this?.apply()
        }
    }

    private fun setLoadingState() {
        binding.loadingState.isVisible = true
    }

    private fun setErrorState() {
        Toast.makeText(
            context,
            "Name of the account is incorrect, pleas try again",
            Toast.LENGTH_LONG
        ).show()
        binding.errorState.isVisible = true
    }
}