package com.example.cinema_app.data.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.Films.CustomDataClass
import com.example.cinema_app.presentation.FilmActivity

class CategoriesRecycleAdapter(private val list: List<String>) :
    RecyclerView.Adapter<CategoriesRecycleAdapter.ViewHolder>() {

    val checked = MutableList(list.size){false}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createItem =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(createItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.nameText.text = currentItem
        holder.checkbox.setOnClickListener{
            checked[position] = !checked[position]
        }
    }

    fun getCheckedNames(): MutableList<String>
    {
        val result:MutableList<String> = mutableListOf()

        for(i in 0..checked.size-1)
        {
            if(checked[i])
                result.add(list[i])
        }
        return result
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameText: TextView
        var checkbox: CheckBox
        init {
            nameText = itemView.findViewById(R.id.categoryName)
            checkbox = itemView.findViewById(R.id.itemCheckBox)
        }
    }
}