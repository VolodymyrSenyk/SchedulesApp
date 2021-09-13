package com.senyk.volodymyr.schedulesapp.presentation.core.provider

import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat

object AttrValuesProvisionUtil {

    @ColorInt
    fun getThemeColorInt(theme: Theme, colorAttr: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(colorAttr, typedValue, true)
        return typedValue.data
    }

    fun getThemeDimenInPixels(theme: Theme, dimenAttr: Int): Float {
        val styledAttributes = theme.obtainStyledAttributes(intArrayOf(dimenAttr))
        val dimenInPixels = styledAttributes.getDimensionPixelSize(0, 0).toFloat()
        styledAttributes.recycle()
        return dimenInPixels
    }

    @DrawableRes
    fun getThemeDrawableRes(theme: Theme, drawableAttr: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(drawableAttr, typedValue, true)
        return typedValue.resourceId
    }

    fun getThemeDrawable(theme: Theme, drawableAttr: Int): Drawable? {
        val typedValue = TypedValue()
        theme.resolveAttribute(drawableAttr, typedValue, true)
        val drawableRes = typedValue.resourceId
        return ResourcesCompat.getDrawable(theme.resources, drawableRes, theme)
    }
}
