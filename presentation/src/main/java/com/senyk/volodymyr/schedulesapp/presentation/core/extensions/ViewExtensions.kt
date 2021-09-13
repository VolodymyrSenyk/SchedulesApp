package com.senyk.volodymyr.schedulesapp.presentation.core.extensions

import android.text.InputFilter
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun TextView.isEmpty() = text.isEmpty()

inline val View.layoutInflater get() = context.layoutInflater

fun View.getRelativeLeftCoordinate(): Int =
    if (parent === rootView) left else left + (parent as View).getRelativeLeftCoordinate()

fun View.getRelativeRightCoordinate(): Int =
    if (parent === rootView) right else right + (parent as View).getRelativeRightCoordinate()

fun View.getRelativeTopCoordinate(): Int =
    if (parent === rootView) top else top + (parent as View).getRelativeTopCoordinate()

fun View?.getRelativeBottomCoordinate(): Int =
    if (this?.parent === this?.rootView) {
        this?.bottom ?: 0
    } else {
        this?.bottom ?: 0 + (this?.parent as View?).getRelativeBottomCoordinate()
    }

fun RecyclerView.getLastVisibleItemView(): View? {
    val layoutManager = layoutManager as? LinearLayoutManager ?: layoutManager as GridLayoutManager
    val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
    return getChildAt(lastVisiblePosition)
}
