package ru.ratanov.mobile.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import org.jetbrains.anko.textColor
import ru.ratanov.mobile.view.main.bottomsheet.FilterProducer

@SuppressLint("ViewConstructor")
class FilterRadioButton(
    context: Context,
    private val valueName: String,
    producer: FilterProducer,
    private val onFilterSelected: (String) -> Unit
) : LinearLayout(context) {

    var active: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                invalidate()
            }
        }

    private val valueView = AppCompatTextView(context).apply {
        text = valueName
    }

    init {
        orientation = HORIZONTAL
        addView(valueView)

        producer.attach { selectedValue ->
            active = selectedValue == valueName
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        valueView.textColor = if (active) Color.GREEN else Color.GRAY
        drawChild(canvas, valueView, drawingTime)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            onFilterSelected.invoke(this.valueName)
            return true
        }

        return super.onTouchEvent(event)
    }

}