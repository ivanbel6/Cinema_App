package com.example.cinema_app.data

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R

class GenreAdapter(private val genreList: List<String>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_card_item, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genreList[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genreNameTextView: TextView = itemView.findViewById(R.id.genreNameTextView)

        fun bind(genre: String) {
            genreNameTextView.text = genre

        }
    }
}