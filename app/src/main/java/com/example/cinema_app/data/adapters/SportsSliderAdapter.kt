package com.example.cinema_app.data.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.Films.SlideItem
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SlideSportItem
import com.google.android.material.imageview.ShapeableImageView

class SportsSliderAdapter(
    private val itemList: List<SlideSportItem>,
    private val context: Context,
    ) :
    RecyclerView.Adapter<SportsSliderAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val location: TextView = itemView.findViewById(R.id.location)
        val nameSportEvent: TextView = itemView.findViewById(R.id.nameSportEvent)
        val mainImage: ShapeableImageView = itemView.findViewById(R.id.MainIMG)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sports_slide_item, parent, false)
        return ViewHolder(view)
    }


    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.date.text = item.date
        holder.location.text = item.location
        if(position == 0)
            Glide.with(holder.itemView)
                .load("https://lishop.by/upload/iblock/4c0/4c0615d52e5e65b27384f15667ccb18e.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mainImage)
        else if(position == 1)
            Glide.with(holder.itemView)
                .load("https://metaratings.ru/_images/insecure/h-520/bG9jYWw6Ly8vL3VwbG9hZC9zcHJpbnQuZWRpdG9yLzc5MS83OTExYjJjZmYxNjYxZGRmMzIzZTA3YjM1NWU0MmExYS5qcGc=.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mainImage)
        else if(position == 2)
            Glide.with(holder.itemView)
                .load("https://atlas-sport.ru/image/catalog/stati/basketball/istock-959080376_d_850.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mainImage)
        else if(position == 3)
            Glide.with(holder.itemView)
                .load("https://sport-pulse.kz/wp-content/uploads/2023/06/preimuchestva-basketbola.jpeg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mainImage)
        else if(position == 4)
            Glide.with(holder.itemView)
                .load("https://s-cdn.sportbox.ru/images/styles/upload/fp_fotos/30/48/5ea05f9b45da1cd33de97675127445bd615dcd065c65b967211729.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mainImage)
        holder.nameSportEvent.text = item.nameSportEvent

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}