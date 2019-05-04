package ru.ratanov.mobile.view

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout


fun View.expand() {
    this.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    val targetHeight = this.measuredHeight
    this.layoutParams.height = 0
    this.visibility = View.VISIBLE

    val animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            this@expand.layoutParams.height = if (interpolatedTime == 1f)
                LinearLayout.LayoutParams.WRAP_CONTENT
            else (targetHeight * interpolatedTime).toInt()
            this@expand.requestLayout()
        }

        override fun willChangeBounds() = true
    }.apply {
        startOffset = 200
        duration = (targetHeight / this@expand.context.resources.displayMetrics.density).toLong()
    }

    this.startAnimation(animation)
}


fun View.collapse() {
    val initialHeight = this.measuredHeight

    val animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            if (interpolatedTime == 1f) {
                this@collapse.visibility = View.GONE
            } else {
                this@collapse.layoutParams.height = (initialHeight - initialHeight * interpolatedTime).toInt()
                this@collapse.requestLayout()
            }
        }

        override fun willChangeBounds() = true
    }.apply {
        duration = (initialHeight / this@collapse.context.resources.displayMetrics.density).toLong()
    }

    this.startAnimation(animation)
}

