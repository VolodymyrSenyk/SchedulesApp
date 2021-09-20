package com.senykvolodymyr.core.livedata.event.navigation

data class RequestPermissionsEvent(
    val permissions: List<String>,
    val requestCode: Int
) : NavigationEvent
