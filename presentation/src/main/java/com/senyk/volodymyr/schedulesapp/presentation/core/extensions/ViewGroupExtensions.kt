package com.senyk.volodymyr.schedulesapp.presentation.core.extensions

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.senyk.volodymyr.schedulesapp.presentation.core.extensions.layoutInflater

fun ViewGroup.inflateView(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
    layoutInflater.inflate(layoutId, this, attachToRoot)
