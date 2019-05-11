package ru.ratanov.mobile.view.main.bottomsheet

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import ru.ratanov.core.model.Filter
import ru.ratanov.mobile.widget.FilterCard

@SuppressLint("ViewConstructor")
class FilterFragmentView(
    context: Context,
    initFilters: List<Filter>
) : ScrollView(context) {

    private val collapsingProducer = FilterProducer("")

    private val contentView = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL

        initFilters.forEach {
            addView(
                FilterCard(context, it, collapsingProducer, this@FilterFragmentView::onCollapsed)
            )
        }
    }

    init {
        foregroundGravity = Gravity.BOTTOM
        addView(contentView)
    }


    private fun onCollapsed(card: String) {
        collapsingProducer.setValue(card)
    }
}