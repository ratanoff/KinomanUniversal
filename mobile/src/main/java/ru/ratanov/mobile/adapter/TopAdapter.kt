package ru.ratanov.mobile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.ratanov.core.model.TopFilm
import ru.ratanov.mobile.view.TopItemView


class TopAdapter(private val items: List<TopFilm>, private val topPosterClickListener: TopPosterClickListener) :
    RecyclerView.Adapter<TopAdapter.TopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(TopItemView(parent.context))
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        val posterUrl = items[position].posterUrl

        Picasso.get()
            .load(posterUrl)
            .into(holder.itemView as ImageView)

        holder.itemView.transitionName = posterUrl

        holder.itemView.setOnClickListener {
            topPosterClickListener.onTopPosterClick(posterUrl, holder.itemView)
        }
    }

    override fun getItemCount() = items.size

    class TopViewHolder(val posterView: ImageView) : RecyclerView.ViewHolder(posterView) {

    }

}


