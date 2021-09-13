package com.senyk.volodymyr.schedulesapp.presentation.core.util

import android.os.CountDownTimer
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView

object ShowCustomToastUtil {

    private const val animationDuration = 300L

    operator fun invoke(view: TextView, message: String, duration: ToastDuration) {
        val animation = AlphaAnimation(0f, 1f).apply {
            this.duration = animationDuration
            fillAfter = true
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    object : CountDownTimer(duration.timeInMillis, 1) {
                        override fun onTick(millisUntilFinished: Long) {}
                        override fun onFinish() {
                            hideCustomToast(view)
                        }
                    }.start()
                }
            })
        }
        view.apply {
            text = message
            visibility = View.VISIBLE
            startAnimation(animation)
        }
    }

    private fun hideCustomToast(view: TextView) {
        val animation = AlphaAnimation(1f, 0f).apply {
            duration = animationDuration
            fillAfter = true
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    view.visibility = View.GONE
                }
            })
        }
        view.startAnimation(animation)
    }

    enum class ToastDuration(val timeInMillis: Long) {
        SHORT(2_600), MEDIUM(4_000), LONG(6_000)
    }
}
