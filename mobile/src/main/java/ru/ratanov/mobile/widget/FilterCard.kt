package ru.ratanov.mobile.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.sp
import org.jetbrains.anko.textColor
import ru.ratanov.core.model.Filter
import ru.ratanov.mobile.R
import ru.ratanov.mobile.view.collapse
import ru.ratanov.mobile.view.expand
import ru.ratanov.mobile.view.main.bottomsheet.FilterProducer

@SuppressLint("ViewConstructor")
class FilterCard(context: Context, filter: Filter) : LinearLayout(context) {

    private var expanded = false

    private val producer = FilterProducer("")

    private val titleView = AppCompatTextView(context).apply {
        text = filter.title
        textSize = sp(8).toFloat()
        textColor = Color.BLACK
        typeface = ResourcesCompat.getFont(context, R.font.google_sans)
    }

    private val expandIcon = ImageView(context).apply {
        setImageResource(R.drawable.ic_expand_more)
        imageTintList = AppCompatResources.getColorStateList(context, R.color.collapsing_section)
        layoutParams = RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            addRule(RelativeLayout.ALIGN_PARENT_END)
        }
    }

    private val titleContainer = RelativeLayout(context).apply {
        addView(titleView)
        addView(expandIcon)
        addRipple(this)
        setOnClickListener { toggleExpand() }
    }

    private val contentView = LinearLayout(context).apply {
        orientation = VERTICAL
        visibility = View.GONE

        producer.setValue(filter.params[0].value)

        filter.params.forEach {
            addView(FilterRadioButton(context, it.value, producer, this@FilterCard::onFilterSelected))
        }
    }

    init {

        orientation = VERTICAL
        padding = dip(16)

        addView(titleContainer)
        addView(contentView)

    }

    private fun toggleExpand() {
        if (expanded) contentView.collapse() else contentView.expand()
        expanded = !expanded
        expandIcon.animate().rotation(if (expanded) 180f else 0f).duration = 200
        expandIcon.isActivated = expanded

    }

    private fun addRipple(view: View) {
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        view.isClickable = true
        view.setBackgroundResource(outValue.resourceId)
    }

    private fun onFilterSelected(value: String) {
        producer.setValue(value)
    }
}