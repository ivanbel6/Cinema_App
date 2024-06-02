package com.example.cinema_app.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.CyberSportEvent.ProcessedCyberSportEvent


class CyberSportRecycleAdapter(private val list: List<ProcessedCyberSportEvent>) :
    RecyclerView.Adapter<CyberSportRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createItem = LayoutInflater.from(parent.context).inflate(R.layout.cybersport_item, parent, false)
        return ViewHolder(createItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.disciplineName.text = currentItem.disciplineName
        holder.time.text = currentItem.time

        Glide.with(holder.itemView)
            .load(currentItem.team1LogoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.team1Logo)

        Glide.with(holder.itemView)
            .load(currentItem.team2LogoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.team2Logo)

        holder.date.text = currentItem.date

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var disciplineName: TextView
        var time: TextView
        var team1Logo: ImageView
        var team2Logo: ImageView
        var date: TextView

        init {
            disciplineName = itemView.findViewById(R.id.cyberSportDisciplineName)
            time = itemView.findViewById(R.id.cyberSportEventTime)
            team1Logo = itemView.findViewById(R.id.cyberSportTeam1Logo)
            team2Logo = itemView.findViewById(R.id.cyberSportTeam2Logo)
            date = itemView.findViewById(R.id.cyberSportEventDate)
        }
    }
}