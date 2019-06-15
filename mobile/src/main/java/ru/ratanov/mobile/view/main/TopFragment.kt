package ru.ratanov.mobile.view.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.recycler_view.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.defaultSharedPreferences
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.mobile.R
import ru.ratanov.mobile.adapter.TopAdapter
import ru.ratanov.mobile.adapter.TopPosterClickListener
import ru.ratanov.mobile.view.base.BaseFragment


class TopFragment : BaseFragment(), TopPosterClickListener {

    private val TAG = this::class.java.simpleName

    companion object {
        private val ARG_TAB = "arg_tab"
        fun newInstance(tab: String): TopFragment {
            return TopFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TAB, tab)
                }
            }
        }
    }

    private val sharedPrefListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
        if (key == arguments?.getString(ARG_TAB)) {
            toast("changed: ${sharedPreferences.getString(key, "")}")
        }
    }

    override fun getLayout() = R.layout.recycler_view

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view.recyclerView) {
            if (adapter != null) {
                hideLoading()
                return@with
            }

            layoutManager = GridLayoutManager(view.context, 3)
            setHasFixedSize(true)
            doAsync {
                val items = FilmRepository.getTopFilms()
                uiThread {
                    items?.let {
                        adapter = TopAdapter(it, this@TopFragment)
                        hideLoading()
                    }
                }
            }
        }
    }

    override fun onTopPosterClick(posterUrl: String, sharedImageView: ImageView) {
        findNavController().navigate(
            R.id.action_topFragment_to_detailFragment,
            bundleOf("extra_poster_url" to posterUrl)
        )
    }

    override fun onResume() {
        super.onResume()
        defaultSharedPreferences.registerOnSharedPreferenceChangeListener(sharedPrefListener)
    }

    override fun onPause() {
        super.onPause()
        defaultSharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPrefListener)
    }

}
