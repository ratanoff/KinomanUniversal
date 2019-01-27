package ru.ratanov.mobile.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import org.jetbrains.anko.windowManager

class ProgressWidget : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val defaultDisplay = context.windowManager.defaultDisplay
    private val screenSize = Point().apply { defaultDisplay.getSize(this) }

    private val COLOR_START = Color.parseColor("#004487d6")
    private val COLOR_END = Color.parseColor("#4487d6")
    private val gradient = SweepGradient(screenSize.x / 2f, screenSize.y / 2f, COLOR_START, COLOR_END)
    private val gradientMatrix = Matrix()
    private var startAngle = 0f
    private val rectF = RectF()

    private val arcPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawARGB(200, 255, 255, 255)

        val width = screenSize.x.toFloat()
        val height = screenSize.y.toFloat()

        val size = width / 3

        rectF.left = (width - size) / 2
        rectF.top = (height - size) / 2
        rectF.right = rectF.left + size
        rectF.bottom = rectF.top + size

        gradientMatrix.reset()
        gradientMatrix.preRotate(-startAngle, width / 2, height / 2)
        gradient.setLocalMatrix(gradientMatrix)
        arcPaint.shader = gradient

        canvas.drawArc(rectF, 0f, 360f, true, arcPaint)
    }


    private fun animateArc() {
        ValueAnimator.ofFloat(360f, 0f).apply {
            duration = 800
            interpolator = LinearInterpolator()
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                startAngle = it.animatedValue as Float
                invalidate()
            }
        }.start()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animateArc()
    }
}