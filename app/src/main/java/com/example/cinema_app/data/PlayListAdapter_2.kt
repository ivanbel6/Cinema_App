package com.example.cinema_app.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.R
import com.example.cinema_app.data.DB.Entities.PlaylistWithFilms


class PlayListAdapter_2(private val newList: List<PlaylistWithFilms>) :
    RecyclerView.Adapter<PlayListAdapter_2.PlayListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.play_list_item_2, parent, false)
        return PlayListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        val currentItem = newList[position]

        holder.titleTextView.text = currentItem.playlist.name
        holder.descriptionTextView.text = currentItem.playlist.description

        Glide.with(holder.itemView)
            .load(currentItem.films[0].backdropURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.cardImageView)
        // Set image for cardImageView using currentItem.imageUrl

        // Set click listener for the entire item
        holder.itemView.setOnClickListener {
            // Handle item click event here
        }
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    inner class PlayListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description)
        val cardImageView: ImageView = itemView.findViewById(R.id.cardImageView)
    }
}