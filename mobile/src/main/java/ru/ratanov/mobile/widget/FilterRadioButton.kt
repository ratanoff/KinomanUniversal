package ru.ratanov.mobile.widget

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView

@SuppressLint("ViewConstructor")
class FilterRadioButton(context: Context, valueName: String) : LinearLayout(context) {

    private val valueView = TextView(context).apply { text = valueName }
    private val radioButton = RadioButton(context)

    init {
        orientation = HORIZONTAL
        addView(valueView)
        addView(radioButton)
    }
}