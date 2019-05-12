package ru.ratanov.mobile.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
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
import ru.ratanov.mobile.prefs.TabsSettingsHolder
import ru.ratanov.mobile.view.addRipple
import ru.ratanov.mobile.view.collapse
import ru.ratanov.mobile.view.expand
import ru.ratanov.mobile.view.main.bottomsheet.FilterProducer

@SuppressLint("ViewConstructor")
class FilterCard(
    context: Context,
    private val filter: Filter,
    collapsingProducer: FilterProducer,
    private val onCollapsing: (String) -> Unit
) : LinearLayout(context) {

    private var expanded = false

    private val producer = FilterProducer("")

    private val titleView = AppCompatTextView(context).apply {
        id = View.generateViewId()
        text = "${filter.title}: "
        textSize = sp(8).toFloat()
        textColor = Color.BLACK
        typeface = ResourcesCompat.getFont(context, R.font.google_sans)
    }

    private val valueView = AppCompatTextView(context).apply {
        textSize = sp(8).toFloat()
        textColor = Color.BLUE
        typeface = ResourcesCompat.getFont(context, R.font.google_sans)

        layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
            addRule(RelativeLayout.RIGHT_OF, titleView.id)
        }
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
        addView(valueView)
        addView(expandIcon)
        padding = dip(16)
        setOnClickListener { toggleExpand() }
        addRipple()
    }

    private val contentView = LinearLayout(context).apply {
        orientation = VERTICAL
        visibility = View.GONE
        setPadding(dip(16), 0, dip(16), dip(16))

        producer.setValue(filter.params[0].name)
        valueView.text = filter.params[0].name

        filter.params.forEach {
            addView(FilterRadioButton(context, it.name, producer, this@FilterCard::onFilterSelected))
        }
    }

    init {
        orientation = VERTICAL

        addView(titleContainer)
        addView(contentView)

        collapsingProducer.attach { selectedCard ->
            if (selectedCard != filter.key) {
                expanded = false
                contentView.collapse()
                expandIcon.animate().rotation(0f).duration = 200
                expandIcon.isActivated = expanded
            }
        }
    }

    private fun toggleExpand() {
        if (expanded) contentView.collapse() else contentView.expand()
        expanded = !expanded
        expandIcon.animate().rotation(if (expanded) 180f else 0f).duration = 200
        expandIcon.isActivated = expanded

        if (expanded) onCollapsing.invoke(filter.key)
    }

    private fun onFilterSelected(value: String) {
        Log.d("Filter", "$value selected") //todo check why twice invoked?
        producer.setValue(value)
        valueView.text = value
        TabsSettingsHolder.set(filter.key, value)
    }
}