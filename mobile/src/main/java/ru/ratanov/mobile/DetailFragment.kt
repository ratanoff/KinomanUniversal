package ru.ratanov.mobile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.view.*


class DetailFragment : Fragment() {

    companion object {
        private val EXTRA_POSTER_URL = "extra_poster_url"

        fun newInstance(posterUrl: String): DetailFragment {
            val bundle = Bundle().apply {
                putString(EXTRA_POSTER_URL, posterUrl)
            }
            return DetailFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(EXTRA_POSTER_URL)?.let {
            view.poster.transitionName = it
            Picasso.get().load(it).into(view.poster)
        }
    }


}
