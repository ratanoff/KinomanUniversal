package ru.ratanov.mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.recycler_view.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.ratanov.core.model.TopFilm
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.core.urls.Endpoints
import ru.ratanov.mobile.adapter.TopAdapter
import ru.ratanov.mobile.adapter.TopPosterClickListener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class TopFragment : Fragment(), TopPosterClickListener {

    companion object {
        fun newInstance() = TopFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val raw = resources.openRawResource(R.raw.top)
        val rd = BufferedReader(InputStreamReader(raw))
        val items = GsonBuilder().create()
            .fromJson<List<TopFilm>>(rd, object : TypeToken<List<TopFilm>>() {}.type)

        with(view.recyclerView) {
            layoutManager = GridLayoutManager(view.context, 3)
            setHasFixedSize(true)
            adapter = TopAdapter(items, this@TopFragment)
//            doAsync {
//                val items = FilmRepository.getTopFilms()
//                uiThread { items?.let { adapter = TopAdapter(it, this@TopFragment) } }
//            }
        }
    }

    override fun onTopPosterClick(posterUrl: String, sharedImageView: ImageView) {
        val detailFragment = DetailFragment.newInstance(posterUrl)
        fragmentManager
            ?.beginTransaction()
            ?.addSharedElement(sharedImageView, ViewCompat.getTransitionName(sharedImageView)!!)
            ?.addToBackStack(TopFragment::class.java.simpleName)
            ?.replace(R.id.content, detailFragment)
            ?.commit()

    }

}
