package com.senykvolodymyr.core.livedata.event.navigation

import androidx.navigation.NavDirections

data class NavigateToFragmentEvent(val action: NavDirections) : NavigationEvent
