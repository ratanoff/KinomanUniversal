package ru.ratanov.mobile.view.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tabs.*

import ru.ratanov.mobile.R
import ru.ratanov.mobile.view.main.TopFragment


class TabsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fragment_tabs, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.offscreenPageLimit = 2
        viewPager.adapter = FilmPagerAdapter(childFragmentManager).apply {
            addFragment(TopFragment(), "Films")
            addFragment(TopFragment(), "Serials")
            addFragment(TopFragment(), "Mults")
        }
        tabLayout.setupWithViewPager(viewPager)
    }

}
