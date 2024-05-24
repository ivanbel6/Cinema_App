package com.example.cinema_app.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R

class PlayListAdapter(private val newList: List<String>) : RecyclerView.Adapter<PlayListAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_play_list, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val name = newList[position]
        holder.bind(name)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playListName: TextView = itemView.findViewById(R.id.PlayListItemTextView)

        fun bind(currentName: String) {
            playListName.text = currentName

        }
    }
}