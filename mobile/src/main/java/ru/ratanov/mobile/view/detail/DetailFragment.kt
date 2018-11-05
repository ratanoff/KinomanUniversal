package ru.ratanov.mobile.view.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.view.*
import ru.ratanov.mobile.R
import ru.ratanov.mobile.view.base.BaseFragment


class DetailFragment : BaseFragment() {

    override fun getLayout() = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()

        arguments?.getString("extra_poster_url")?.let {
            Picasso.get().load(it).into(view.poster)
        }
    }


}
