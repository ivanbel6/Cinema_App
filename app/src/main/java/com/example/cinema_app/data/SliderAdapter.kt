package com.example.cinema_app.data

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.R
import com.google.android.material.imageview.ShapeableImageView

class SliderAdapter(private val itemList: List<SlideItem>,private val context: Context) :
    RecyclerView.Adapter<SliderAdapter.ViewHolder>() {
    private var currentActivePosition: Int = 0
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val genres: TextView = itemView.findViewById(R.id.genres)
        val mainImage: ShapeableImageView = itemView.findViewById(R.id.MainIMG)
        //val imdbIcon: ShapeableImageView = itemView.findViewById(R.id.imdbImage)
        val imdbRating: TextView = itemView.findViewById(R.id.imdbRating)
        //val tomatoesImage: ShapeableImageView = itemView.findViewById(R.id.tomatoesImage)
        val tomatoesRating: TextView = itemView.findViewById(R.id.tomatoesRating)

        val indicator: View = itemView.findViewById(R.id.indicator)
        val indicator2: View = itemView.findViewById(R.id.indicator2)
        val indicator3: View = itemView.findViewById(R.id.indicator3)
        val indicator4: View = itemView.findViewById(R.id.indicator4)
        val indicator5: View = itemView.findViewById(R.id.indicator5)
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
        holder.genres.text = item.genres.toString()
        Glide.with(context).load(item.mainImage).into(holder.mainImage)

        Glide.with(holder.itemView)
            .load(item.mainImage.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.mainImage)



        holder.imdbRating.text = item.imdbRating.toString()
        holder.tomatoesRating.text = item.tomatoesRating.toString()

        // Setting indicator for active and inactive slides
        if (position == currentActivePosition) {
            when(currentActivePosition){
                0 -> holder.indicator.setBackgroundResource(R.drawable.active_indicator)
                1 -> holder.indicator2.setBackgroundResource(R.drawable.active_indicator)
                2 -> holder.indicator3.setBackgroundResource(R.drawable.active_indicator)
                3 -> holder.indicator4.setBackgroundResource(R.drawable.active_indicator)
                4 -> holder.indicator5.setBackgroundResource(R.drawable.active_indicator)
            }


        } else {
            holder.indicator.setBackgroundResource(R.drawable.inactive_indicator)
            holder.indicator2.setBackgroundResource(R.drawable.inactive_indicator)
            holder.indicator3.setBackgroundResource(R.drawable.inactive_indicator)
            holder.indicator4.setBackgroundResource(R.drawable.inactive_indicator)
            holder.indicator5.setBackgroundResource(R.drawable.inactive_indicator)
        }
    }
    // Add function to update indicator
    fun updateIndicator(position: Int) {
        currentActivePosition = position
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return itemList.size

    }


}