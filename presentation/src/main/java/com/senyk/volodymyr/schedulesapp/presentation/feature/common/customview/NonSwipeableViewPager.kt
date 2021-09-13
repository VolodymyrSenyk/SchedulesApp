package com.senyk.volodymyr.schedulesapp.presentation.feature.common.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NonSwipeableViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean = false
}
