package com.example.cinema_app.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.data.DB.Entities.Playlist

class PlayListAdapter(private val newList: List<Playlist>) : RecyclerView.Adapter<PlayListAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_play_list, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val playlist = newList[position]
        holder.bind(playlist)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    fun getAllItems(): List<Playlist> {
        return newList
    }

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playListName: TextView = itemView.findViewById(R.id.PlayListItemTextView)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)

        fun bind(playlist: Playlist) {
            playListName.text = playlist.name
            checkBox.isChecked = playlist.isSelected // Assuming there's a property isSelected in your Playlist class

            // Set a click listener for the checkbox
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                // Update the isSelected property in your Playlist object
                playlist.isSelected = isChecked
                // Perform any other actions based on checkbox state change
            }

            // Set a click listener for the item view
            itemView.setOnClickListener {
                // Perform actions when the item is clicked
            }
        }
    }

    class PlayListViewHolder {

    }
}