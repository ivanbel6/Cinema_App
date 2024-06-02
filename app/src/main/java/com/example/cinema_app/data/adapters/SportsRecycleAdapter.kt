package com.example.cinema_app.data.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SportEvent
import com.example.cinema_app.presentation.SportActivity

class SportsRecycleAdapter(private val list: List<SportEvent>) :
    RecyclerView.Adapter<SportsRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createItem = LayoutInflater.from(parent.context).inflate(R.layout.sport_item, parent, false)
        return ViewHolder(createItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.team1Name.text = currentItem.team1Name
        holder.team2Name.text = currentItem.team2Name
        holder.dateTime.text = currentItem.dateTime

        Glide.with(holder.itemView)
            .load(currentItem.team1LogoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.team1Logo)

        Glide.with(holder.itemView)
            .load(currentItem.team2LogoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.team2Logo)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SportActivity::class.java)
            intent.putExtra("data", currentItem)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var team1Name: TextView
        var team2Name: TextView
        var dateTime: TextView
        var team1Logo: ImageView
        var team2Logo: ImageView

        init {
            team1Name = itemView.findViewById(R.id.team1NameTextView)
            team2Name = itemView.findViewById(R.id.team2NameTextView)
            dateTime = itemView.findViewById(R.id.datetimeTextView)
            team1Logo = itemView.findViewById((R.id.team1ImageView))
            team2Logo = itemView.findViewById((R.id.team2ImageView))
        }
    }
}