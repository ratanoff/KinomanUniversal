package ru.ratanov.mobile.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_mobile.*

abstract class BaseFragment : Fragment() {

    abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    protected fun showLoading() { activity?.progressBar?.visibility = View.VISIBLE }

    protected fun hideLoading() { activity?.progressBar?.visibility = View.INVISIBLE }
}