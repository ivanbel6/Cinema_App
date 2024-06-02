package com.example.cinema_app.domain.UseCases

import com.example.cinema_app.data.adapters.CategoriesRecycleAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.data.Categories

class CreateCategoriesRecycleView {

    fun create(categoriesRecycleView: RecyclerView, category:String) {
        var list: List<String> = emptyList()

        if(category == "all")
            list = Categories.getAllCategories()
        else if(category == "movies" || category == "series")
            list = Categories.getMoviesSeriesCategories()
        else
            list = Categories.getSportsCategories()

        val categoriesAdapter = CategoriesRecycleAdapter(list)
        categoriesRecycleView.adapter = categoriesAdapter
    }
}