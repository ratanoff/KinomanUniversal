package ru.ratanov.mobile.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import org.jetbrains.anko.*
import ru.ratanov.core.model.Filter
import ru.ratanov.mobile.R

@SuppressLint("ViewConstructor")
class FilterCard(context: Context, filter: Filter) : LinearLayout(context) {

    private val titleView = AppCompatTextView(context).apply {
        text = filter.title
        textSize = 16f
        textColor = Color.BLACK
        typeface = ResourcesCompat.getFont(context, R.font.google_sans)
    }


    init {
        orientation = VERTICAL
        padding = dip(16)
        addRipple(this)


        addView(titleView)
    }

    private fun addRipple(view: View) {
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        view.isClickable = true
        view.setBackgroundResource(outValue.resourceId)
    }
}