package ru.ratanov.mobile.view.main.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.bundleOf
import ru.ratanov.core.model.Filter
import ru.ratanov.mobile.App

class FilterFragment : RoundedBottomSheetDialogFragment() {

    companion object {
        private lateinit var initFilters: List<Filter>

        fun newInstance(filters: List<Filter>): FilterFragment {
            initFilters = filters
            return FilterFragment().apply {
                arguments = bundleOf("filters" to filters)
            }
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_filter, container, false)
        return FilterFragmentView(App.instance(), initFilters)
    }
}