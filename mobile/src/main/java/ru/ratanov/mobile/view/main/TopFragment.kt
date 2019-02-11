package ru.ratanov.mobile.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.recycler_view.view.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.mobile.R
import ru.ratanov.mobile.adapter.TopAdapter
import ru.ratanov.mobile.adapter.TopPosterClickListener
import ru.ratanov.mobile.view.base.BaseFragment


class TopFragment : BaseFragment(), TopPosterClickListener {

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

    override fun onTopPosterClick(filmUrl: String) {
        findNavController().navigate(
            R.id.action_topFragment_to_detailFragment,
            bundleOf("extra_film_url" to filmUrl)
        )
    }


}
