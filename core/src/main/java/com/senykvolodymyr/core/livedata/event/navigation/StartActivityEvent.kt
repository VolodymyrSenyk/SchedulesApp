package com.senykvolodymyr.core.livedata.event.navigation

import android.content.Intent

data class StartActivityEvent(val intent: Intent) : NavigationEvent
