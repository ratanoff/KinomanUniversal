package ru.ratanov.mobile.view.detail


import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread
import ru.ratanov.core.repo.FilmRepository
import ru.ratanov.core.repo.TrailerRepository
import ru.ratanov.mobile.R
import ru.ratanov.mobile.view.base.BaseFragment


class DetailFragment : BaseFragment() {

    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var playbackPosition = 0L
    private var currentWindow = 0

    override fun getLayout() = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
//        toolbar.title = "Фильм"
        toolbar.inflateMenu(R.menu.detail_top_menu)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }



        arguments?.getString("extra_film_url")?.let {filmUrl ->

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

                    doAsync {
                        val trailerUrl = TrailerRepository.getTrailer(filmUrl)
                        uiThread {
                            initializePlayer(Uri.parse(trailerUrl))
                            trailer_bg.visibility = View.INVISIBLE
                        }
                    }


                }


            }


            /*doAsync {
                val placeholder = FilmRepository.getPoster(it)
                uiThread {
                    Picasso.get().load(placeholder).fit().centerCrop().into(view.trailer_bg)
                }
            }*/

            /*doAsync {
                val trailerUrl = TrailerRepository.getTrailer()
                uiThread {
                    initializePlayer(Uri.parse(trailerUrl))
                    trailer_bg.visibility = View.INVISIBLE
                    hideLoading()
                }
            }*/
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun initializePlayer(uri: Uri) {
        player = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(view?.context),
            DefaultTrackSelector(),
            DefaultLoadControl()
        )

        video_view.player = player

        player?.playWhenReady = playWhenReady
        player?.seekTo(currentWindow, playbackPosition)
        val mediaSource = buildMediaSource(uri)
        player?.prepare(mediaSource)
        video_view.hideController()
    }

    private fun releasePlayer() {
        player?.let {
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.release()
            player = null
        }
    }

    private fun buildMediaSource(uri: Uri) =
        ExtractorMediaSource.Factory(DefaultHttpDataSourceFactory("kinoman"))
            .createMediaSource(uri)

}
