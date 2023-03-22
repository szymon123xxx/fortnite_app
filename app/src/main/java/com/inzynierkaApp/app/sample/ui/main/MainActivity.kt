package com.inzynierkaApp.app.sample.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inzynierkaApp.app.core.base.BaseActivity
import com.inzynierkaApp.app.sample.R
import com.inzynierkaApp.app.sample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewState, MainViewEvent, MainViewModel>() {

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        this.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val navController = findNavController(R.id.nav_host_fragment_activity_login)
        navView.setupWithNavController(navController)
    }

    @Suppress("EmptyFunctionBlock")
    override fun handleViewState(viewState: MainViewState) {}
}
