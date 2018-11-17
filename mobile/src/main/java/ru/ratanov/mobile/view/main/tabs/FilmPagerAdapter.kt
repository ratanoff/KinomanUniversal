package ru.ratanov.mobile.view.main.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FilmPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = titleList[position]

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

}