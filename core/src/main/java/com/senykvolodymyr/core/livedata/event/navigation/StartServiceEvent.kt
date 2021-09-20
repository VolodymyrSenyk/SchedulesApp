package com.senykvolodymyr.core.livedata.event.navigation

import android.content.Intent

data class StartServiceEvent(
    val intent: Intent
) : NavigationEvent
