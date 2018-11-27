package ru.ratanov.mobile.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import ru.ratanov.core.model.Filter
import ru.ratanov.mobile.R

@SuppressLint("ViewConstructor")
class FilterCard(context: Context, filter: Filter) : LinearLayout(context) {

    private val expandIcon = ImageView(context).apply {
        setImageResource(R.drawable.ic_expand_more)
        setColorFilter(ContextCompat.getColor(context, R.color.collapsing_section), PorterDuff.Mode.SRC_IN)

        layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
            addRule(RelativeLayout.ALIGN_END)
        }
    }

    private val titleContainer = RelativeLayout(context).apply {
        val attrs = intArrayOf(R.attr.selectableItemBackground, android.R.attr.listPreferredItemHeightSmall)
        val typedArray = context.obtainStyledAttributes(attrs)
        val backgroundResource = typedArray.getResourceId(0, 0)
        val minHeight = typedArray.getResourceId(0, 1)
        setBackgroundResource(backgroundResource)
        minimumHeight = minHeight
        typedArray.recycle()
    }

    init {
        orientation = VERTICAL
    }
}