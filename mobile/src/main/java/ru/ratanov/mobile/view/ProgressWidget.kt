package ru.ratanov.mobile.view

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import ru.ratanov.mobile.R

class ProgressWidget : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val kinomanLogo: Drawable = context.resources.getDrawable(R.drawable.ic_progress_icon)

    private var startAngle = 0f
    private var sweepAngle = 0f
    private var offset = 20f


    private val rectF = RectF()
    private val rectPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.RED
    }

    private val archPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.WHITE
        strokeWidth = 16f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawARGB(200, 255, 255, 255)

        val size = width / 3

        val left = (width - size) / 2
        val top = (height - size) / 2
        val right = left + size
        val bottom = top + size

        kinomanLogo.setBounds(left, top, right, bottom)
        kinomanLogo.draw(canvas)


        rectF.left = left + offset
        rectF.top = top + offset
        rectF.right = right - offset
        rectF.bottom = bottom - offset

        canvas.drawArc(rectF, startAngle, sweepAngle, false, archPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }

    private fun animateArch() {
        val angle = ValueAnimator.ofFloat(30f, 330f, 30f).apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                sweepAngle = it.animatedValue as Float
                invalidate()
            }
        }

        val rotate = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 800
            interpolator = LinearInterpolator()
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                startAngle = it.animatedValue as Float
                invalidate()
            }
        }

        AnimatorSet().apply {
            playTogether(angle, rotate)
            start()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animateArch()
    }
}