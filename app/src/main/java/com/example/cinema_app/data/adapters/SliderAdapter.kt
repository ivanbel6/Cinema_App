package com.example.cinema_app.data.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.Films.SlideItem
import com.google.android.material.imageview.ShapeableImageView

class SliderAdapter(
    private val itemList: List<SlideItem>,
    private val context: Context,

    ) :
    RecyclerView.Adapter<SliderAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val genres: TextView = itemView.findViewById(R.id.genres)
        val mainImage: ShapeableImageView = itemView.findViewById(R.id.MainIMG)

        //val imdbIcon: ShapeableImageView = itemView.findViewById(R.id.imdbImage)
        val imdbRating: TextView = itemView.findViewById(R.id.imdbRating)

        //val tomatoesImage: ShapeableImageView = itemView.findViewById(R.id.tomatoesImage)
        val tomatoesRating: TextView = itemView.findViewById(R.id.tomatoesRating)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slide_item, parent, false)
        return ViewHolder(view)
    }


    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.date.text = item.date.toString()
        holder.genres.text = item.genres
        Glide.with(context).load(item.mainImage).into(holder.mainImage)

        Glide.with(holder.itemView)
            .load(item.mainImage.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.mainImage)



        holder.imdbRating.text = item.imdbRating.toString()
        holder.tomatoesRating.text = item.tomatoesRating.toString()

        // Setting indicator for active and inactive slides

    }


    override fun getItemCount(): Int {
        return itemList.size

    }


}