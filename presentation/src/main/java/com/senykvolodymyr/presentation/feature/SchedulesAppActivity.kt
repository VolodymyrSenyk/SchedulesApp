package com.senykvolodymyr.presentation.feature

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.senykvolodymyr.core.base.activity.BaseActivity
import com.senykvolodymyr.core.viewmodel.ExitViewModel
import com.senykvolodymyr.presentation.R
import com.senykvolodymyr.presentation.databinding.ActivityMainBinding

class SchedulesAppActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes = R.layout.activity_main

    private val exitAppViewModel by viewModels<ExitViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customToastView = binding.textCustomToast
        exitAppViewModel.exit.observe(this, { exit ->
            if (exit) finish() else super.onBackPressed()
        })
        exitAppViewModel.toastMessage.observe(this, { message ->
            showCustomToast(message)
        })

        val navController = Navigation.findNavController(this, R.id.fragment_host)
        NavigationUI.setupWithNavController(binding.bottomNavView, navController)
    }

    override fun onBackPressed() {
        val navController = Navigation.findNavController(this, R.id.fragment_host)
        exitAppViewModel.onBackPressed(navController)
    }
}
