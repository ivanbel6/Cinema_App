package com.example.cinema_app.data

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.CustomDataClass
import com.example.cinema_app.presentation.FilmActivity

class CustomRecycleAdapter(private val list: List<CustomDataClass>) :
    RecyclerView.Adapter<CustomRecycleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_custom_type, parent, false)
        return ViewHolder(createItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.nameText.text = currentItem.name
        holder.timeText.text = currentItem.time
        holder.dateText.text = currentItem.date.toString()
        Glide.with(holder.itemView)
            .load(currentItem.bgImage.previewUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.bgImage)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, FilmActivity::class.java)
            intent.putExtra("param1", "Hello")
            intent.putExtra("param2", "World")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bgImage: ImageView
        var nameText: TextView
        var timeText: TextView
        var dateText: TextView

        init {
            bgImage = itemView.findViewById(R.id.cardBackgroundImage)
            nameText = itemView.findViewById(R.id.name)
            timeText = itemView.findViewById(R.id.time)
            dateText = itemView.findViewById(R.id.date)
        }
    }
}