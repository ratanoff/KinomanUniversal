package ru.ratanov.mobile.view.detail


import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.mobile.R
import ru.ratanov.mobile.view.base.BaseFragment


class DetailFragment : BaseFragment() {

    override fun getLayout() = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()


        arguments?.getString("extra_film_url")?.let {
            doAsync {
                val posterUrl = FilmRepository.getPoster(it)
                uiThread {
                    Picasso.get().load(posterUrl).into(view.poster)
                    hideLoading()
                }
            }


        }
    }


}
