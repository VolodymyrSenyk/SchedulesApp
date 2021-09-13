package com.senyk.volodymyr.schedulesapp.presentation.core.livedata.event.navigation

import android.content.Intent
import com.senyk.volodymyr.schedulesapp.presentation.core.livedata.event.navigation.NavigationEvent

data class StartActivityEvent(val intent: Intent) : NavigationEvent
