package com.senyk.volodymyr.schedulesapp.presentation.feature

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.presentation.core.base.activity.BaseActivity
import com.senyk.volodymyr.schedulesapp.presentation.core.viewmodel.ExitViewModel

class SchedulesAppActivity : BaseActivity() {

    override val layoutRes = R.layout.activity_main

    private val exitAppViewModel by viewModels<ExitViewModel>(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customToastView = findViewById(R.id.text_view_custom_toast)
        exitAppViewModel.exit.observe(this, { exit ->
            if (exit) finish() else super.onBackPressed()
        })
        exitAppViewModel.toastMessage.observe(this, { message ->
            showCustomToast(message)
        })

        val navController = Navigation.findNavController(this, R.id.fragment_host)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

    override fun onBackPressed() {
        val navController = Navigation.findNavController(this, R.id.fragment_host)
        exitAppViewModel.onBackPressed(navController)
    }
}
