package ru.ratanov.mobile.view.main.bottomsheet

import android.content.Context
import android.widget.LinearLayout
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.mobile.widget.FilterCard

class FilterFragmentView(context: Context): LinearLayout(context) {




    init {
        orientation = VERTICAL

        doAsync {
            val filters = FilmRepository.getFilters()
            uiThread {
                filters.forEach {
                    addView(
                        FilterCard(context, it)
                    )
                }
            }
        }


    }


}