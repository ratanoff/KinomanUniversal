package ru.ratanov.mobile.view.detail


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread
import ru.ratanov.core.model.Film
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.mobile.R
import ru.ratanov.mobile.view.base.BaseFragment


class DetailFragment : BaseFragment() {

    override fun getLayout() = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
//        toolbar.title = "Фильм"
        toolbar.inflateMenu(R.menu.detail_top_menu)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }



        arguments?.getString("extra_film_url")?.let { filmUrl ->

            doAsync {
                val film = FilmRepository.getFilm(filmUrl)
                uiThread {
                    Picasso.get().load(film.bigPosterUrl).fit().centerCrop().into(view.trailer_bg)
                    Picasso.get().load(film.smallPosterUrl).into(view.poster)
                    title.text = film.title
                    original_title.text = film.originalTitle
                    length.text = film.length


                    toast("Film loaded")
                    hideLoading()

                    loadTrailer(film)
                }
            }




        }

    }

    private fun loadTrailer(film: Film) {
        val filmTitle = film.title ?: film.originalTitle ?: return
        doAsync {
            val videoId = FilmRepository.getTrailer(filmTitle)
            uiThread {
                lifecycle.addObserver(video_view)
                video_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        }
    }

}
