package com.example.cinema_app.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.data.Api.DataClasses.CustomDataClass
import com.example.cinema_app.R

class CustomRecycleAdapter(private val list: List<CustomDataClass>) :
    RecyclerView.Adapter<CustomRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_custom_type, parent, false)
        return ViewHolder(createItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        Glide.with(holder.itemView)
            .load(currentItem.bgImage.previewUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.bgImage)
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        holder.rating.text = currentItem.Rating.toString()
        holder.genre.text = currentItem.Genre.toString()

        holder.icon1.setOnClickListener {
            // Действия при нажатии на icon1
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bgImage: ImageView = itemView.findViewById(R.id.cardBackgroundImage)
        val icon1: ImageView = itemView.findViewById(R.id.icon1)
        val title: TextView = itemView.findViewById(R.id.Title)
        val description: TextView = itemView.findViewById(R.id.description)
        val rating: TextView = itemView.findViewById(R.id.Rating)
        val genre: TextView = itemView.findViewById(R.id.Genre)

        init {
            icon1.setOnClickListener {
                // Действия при нажатии на icon1
            }
        }
    }
}


