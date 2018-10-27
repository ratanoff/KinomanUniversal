package ru.ratanov.mobile.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.ratanov.core.model.TopFilm
import ru.ratanov.mobile.view.TopItemView

class TopAdapter(val context: Context, val items: List<TopFilm>) : RecyclerView.Adapter<TopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(TopItemView(context))
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.bindItem(items[position].posterUrl)
    }

    override fun getItemCount() = items.size

}

class TopViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindItem(url: String) = Picasso.get().load(url).into(itemView as? ImageView)
}