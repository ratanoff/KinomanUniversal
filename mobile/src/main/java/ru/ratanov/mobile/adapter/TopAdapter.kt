package ru.ratanov.mobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.ratanov.core.model.TopFilm
import ru.ratanov.mobile.R


class TopAdapter(private val items: List<TopFilm>, private val topPosterClickListener: TopPosterClickListener) :
    RecyclerView.Adapter<TopAdapter.TopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.top_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        val posterUrl = items[position].posterUrl

        Picasso.get()
            .load(posterUrl)
            .into(holder.posterView)

        holder.posterView.transitionName = posterUrl

        holder.itemView.setOnClickListener {
            topPosterClickListener.onTopPosterClick(posterUrl, holder.posterView)
        }
    }

    override fun getItemCount() = items.size

    class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val posterView: ImageView

        init {
            posterView = itemView.findViewById<View>(R.id.item_animal_square_image) as ImageView
        }


    }

}


