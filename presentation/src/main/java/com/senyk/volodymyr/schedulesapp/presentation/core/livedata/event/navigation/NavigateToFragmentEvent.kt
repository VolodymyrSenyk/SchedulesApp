package com.senyk.volodymyr.schedulesapp.presentation.core.livedata.event.navigation

import androidx.navigation.NavDirections

data class NavigateToFragmentEvent(val action: NavDirections) : NavigationEvent
