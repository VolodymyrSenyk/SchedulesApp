package com.senykvolodymyr.core.extensions

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

@ColorInt
fun Context.getColorCompat(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)
