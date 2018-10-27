package ru.ratanov.mobile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import kotlinx.android.synthetic.main.fragment_detail.view.*


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        view.tvName.text = "Hello, ${arguments?.getString("name")}"

        sharedElementEnterTransition = ChangeBounds().apply { duration = 750 }
        sharedElementReturnTransition = ChangeBounds().apply { duration = 750 }

        return view
    }


}
