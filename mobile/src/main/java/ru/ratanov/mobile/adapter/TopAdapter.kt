package ru.ratanov.mobile.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.ratanov.core.model.TopFilm
import ru.ratanov.mobile.view.TopItemView


class TopAdapter(private val context: Context, private val items: List<TopFilm>) :
    RecyclerView.Adapter<TopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val view = TopItemView(context)
        return TopViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.bindItem(items[position].posterUrl)
    }

    override fun getItemCount() = items.size



}

class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(url: String) = Picasso.get()
        .load(url)
        .into(itemView as ImageView)

}
