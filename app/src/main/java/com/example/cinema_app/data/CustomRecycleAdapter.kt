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
        holder.nameText.text = currentItem.name
        holder.timeText.text = currentItem.time.toString()
        holder.dateText.text = currentItem.date.toString()

        Glide.with(holder.itemView)
            .load(currentItem.bgImage.previewUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.bgImage)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bgImage: ImageView = itemView.findViewById(R.id.cardBackgroundImage)
        val nameText: TextView = itemView.findViewById(R.id.name)
        val timeText: TextView = itemView.findViewById(R.id.time)
        val dateText: TextView = itemView.findViewById(R.id.date)

        init {
            // Set click listener if needed
            itemView.setOnClickListener {
                // Handle item click here
            }
        }
    }
}


