package ru.ratanov.mobile.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.view.MotionEvent
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.textColor
import ru.ratanov.mobile.view.addRipple
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
        textColor = Color.BLACK
        padding = dip(8)
        addRipple()
        setOnClickListener { onFilterSelected.invoke(this@FilterRadioButton.valueName) }
    }

    private val checkView = CheckBox(context).apply {
        setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) onFilterSelected.invoke(this@FilterRadioButton.valueName)
        }
    }

    init {
        orientation = HORIZONTAL
        addView(checkView)
        addView(valueView)

        producer.attach { selectedValue ->
            active = selectedValue == valueName
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        valueView.setTypeface(null, if (active) Typeface.BOLD else Typeface.NORMAL)
        checkView.isChecked = active

        drawChild(canvas, checkView, drawingTime)
        drawChild(canvas, valueView, drawingTime)
    }

}