package com.senykvolodymyr.core.provider

import android.content.Context
import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import java.util.*
import javax.inject.Inject

class ResourcesProvider @Inject constructor(private val context: Context) {

    private val resources: Resources = context.resources

    fun getCurrentLocale(): Locale = resources.configuration.locales[0]

    fun getString(@StringRes stringRes: Int): String = resources.getString(stringRes)

    fun getString(@StringRes stringRes: Int, arg1: String): String =
        resources.getString(stringRes, arg1)

    fun getString(@StringRes stringRes: Int, arg1: String, arg2: String): String =
        resources.getString(stringRes, arg1, arg2)

    fun getString(@StringRes stringRes: Int, arg1: String, arg2: String, arg3: String): String =
        resources.getString(stringRes, arg1, arg2, arg3)

    @ColorInt
    fun getColorInt(@ColorRes colorRes: Int): Int = ContextCompat.getColor(context, colorRes)
}
