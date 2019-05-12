package ru.ratanov.mobile.view.main.bottomsheet

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.bundleOf
import ru.ratanov.core.model.Filter
import ru.ratanov.mobile.App
import ru.ratanov.mobile.prefs.TabsSettingsHolder

class FilterFragment : RoundedBottomSheetDialogFragment() {

    companion object {
        private lateinit var initFilters: List<Filter>
        private var page = 0

        fun newInstance(filters: List<Filter>, currentTab: Int): FilterFragment {
            initFilters = filters
            page = currentTab

            return FilterFragment().apply {
                arguments = bundleOf("filters" to filters)
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FilterFragmentView(App.instance(), initFilters)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        TabsSettingsHolder.save(page)
    }
}