package com.senykvolodymyr.core.extensions

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateView(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
    layoutInflater.inflate(layoutId, this, attachToRoot)
