package ru.ratanov.mobile.view.main.bottomsheet

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.ratanov.core.model.Filter
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.mobile.widget.FilterCard

@SuppressLint("ViewConstructor")
class FilterFragmentView(
    context: Context,
    initFilters: List<Filter>
) : ScrollView(context) {

    private val contentView = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL

        initFilters.forEach {
            addView(
                FilterCard(context, it)
            )
        }
    }

    init {
        foregroundGravity = Gravity.BOTTOM
        addView(contentView)
    }

}