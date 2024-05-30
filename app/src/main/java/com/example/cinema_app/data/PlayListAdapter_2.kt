package com.example.cinema_app.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.R
import com.example.cinema_app.data.DB.Entities.PlaylistWithFilms
import com.example.cinema_app.data.Interfaces.PlaylistUpdateListener
import com.example.cinema_app.domain.UseCases.ModalEditBottomSheet

class PlayListAdapter_2(
    private var newList: List<PlaylistWithFilms>,
    private val updateListener: PlaylistUpdateListener
) : RecyclerView.Adapter<PlayListAdapter_2.PlayListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.play_list_item_2, parent, false)
        return PlayListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        val currentItem = newList[position]

        holder.titleTextView.text = currentItem.playlist.name
        holder.descriptionTextView.text = currentItem.playlist.description
        if (currentItem.films != null && !currentItem.films.isEmpty() && currentItem.films.get(0) != null) {
            Glide.with(holder.itemView)
                .load(currentItem.films.get(0).backdropURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.cardImageView);
        } else {
            Glide.with(holder.itemView)
                .load(R.drawable.place_holder_png)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.cardImageView);
        }


        holder.itemView.setOnClickListener {}

        holder.dotsMenu.setOnClickListener {
            val modalBottomSheet = ModalEditBottomSheet(currentItem, updateListener)
            modalBottomSheet.show((holder.itemView.context as AppCompatActivity).supportFragmentManager, ModalEditBottomSheet.TAG)
        }
    }

    override fun getItemCount(): Int {
        return newList.size
    }


    inner class PlayListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description)
        val cardImageView: ImageView = itemView.findViewById(R.id.cardImageView)
        val dotsMenu: ImageView = itemView.findViewById(R.id.dots_menu)
    }
}


