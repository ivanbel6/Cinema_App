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
import com.example.cinema_app.data.Api.DataClasses.Person

class SliderCastAdapter(private val newList: List<Person>) :
    RecyclerView.Adapter<SliderCastAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_activity_cast_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.name.text = currentItem.name
        if (currentItem.description == null || currentItem.description == ""){
            holder.role.text = "Работал(-а) за кулисами"
        }else{
            holder.role.text = currentItem.description
        }

        // Исправить setImageURI на setImageResource
        Glide.with(holder.actorImage)
            .load(currentItem.photo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.actorImage)

    }

    override fun getItemCount(): Int {
        return newList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val role: TextView = itemView.findViewById(R.id.Role)
        val actorImage: ImageView = itemView.findViewById(R.id.Actor_img)
    }
}