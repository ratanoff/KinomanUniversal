package ru.ratanov.mobile.view.detail


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import ru.ratanov.core.repo.TrailerRepository
import ru.ratanov.mobile.R
import ru.ratanov.mobile.view.base.BaseFragment


class DetailFragment : BaseFragment() {

    override fun getLayout() = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()


        arguments?.getString("extra_film_url")?.let {
            doAsync {
                val trailerUrl = TrailerRepository.getTrailer(it)
                uiThread {
                    val trailerIntent = Intent(Intent.ACTION_VIEW)
                    trailerIntent.setDataAndType(Uri.parse(trailerUrl), "video/mp4")
                    startActivity(trailerIntent)

                    hideLoading()
                }
            }


        }
    }


}
