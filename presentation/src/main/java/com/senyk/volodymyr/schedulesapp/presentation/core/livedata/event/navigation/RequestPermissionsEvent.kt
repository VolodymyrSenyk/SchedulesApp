package com.senyk.volodymyr.schedulesapp.presentation.core.livedata.event.navigation

import com.senyk.volodymyr.schedulesapp.presentation.core.livedata.event.navigation.NavigationEvent

data class RequestPermissionsEvent(
    val permissions: List<String>,
    val requestCode: Int
) : NavigationEvent
