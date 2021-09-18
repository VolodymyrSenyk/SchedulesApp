package com.senyk.volodymyr.schedulesapp.presentation.core.extensions

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
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

fun DialogFragment.requestKey(): String = this.javaClass.simpleName

private const val DIALOG_FRAGMENT_RESULT_KEY = "DIALOG_FRAGMENT_RESULT_KEY"

fun DialogFragment.setFragmentResult(result: Parcelable) {
    setFragmentResult(requestKey(), Bundle().apply {
        putParcelable(DIALOG_FRAGMENT_RESULT_KEY, result)
    })
}

fun Fragment.setFragmentResultListener(requestKey: String, callback: (Parcelable) -> Unit) {
    childFragmentManager.setFragmentResultListener(requestKey, viewLifecycleOwner) { _, bundle ->
        bundle.getParcelable<Parcelable>(DIALOG_FRAGMENT_RESULT_KEY)?.let { callback(it) }
    }
}
