package ru.ratanov.mobile.adapter

import android.widget.ImageView

interface TopPosterClickListener {
    fun onTopPosterClick(posterUrl: String, sharedImageView: ImageView)
}