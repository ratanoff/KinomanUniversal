package ru.ratanov.mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_top.*
import kotlinx.android.synthetic.main.fragment_top.view.*
import kotlinx.android.synthetic.main.recycler_view.*
import kotlinx.android.synthetic.main.recycler_view.view.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.mobile.adapter.TopAdapter


class TopFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view =  inflater.inflate(R.layout.recycler_view, container, false)

        with(view.recyclerView) {
            layoutManager = GridLayoutManager(inflater.context, 3)
            setHasFixedSize(true)
            doAsync {
                val items = FilmRepository.getTopFilms()
                uiThread { items?.let { adapter = TopAdapter(inflater.context, it) } }
            }
        }


        /*view.nextButton.setOnClickListener {
            val bundle = bundleOf("name" to etName.text.toString())
            val extras = FragmentNavigatorExtras( sharedLogo to "shared_logo")
            findNavController().navigate(R.id.action_topFragment_to_detailFragment, bundle, null, extras)
        }*/

        return view
    }

}
