package com.example.cinema_app.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.data.Categories
import com.example.cinema_app.data.adapters.CategoriesRecycleAdapter
import com.example.cinema_app.domain.UseCases.CreateCategoriesRecycleView

class SearchFragment : Fragment(), View.OnClickListener {

    var active:String = "all"

    private lateinit var allButton : ToggleButton
    private lateinit var sportsButton : ToggleButton
    private lateinit var moviesButton : ToggleButton
    private lateinit var seriesButton : ToggleButton
    private lateinit var categoriesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allButton = requireView().findViewById(R.id.radioButtonAll)
        sportsButton = requireView().findViewById(R.id.radioButtonSports)
        moviesButton = requireView().findViewById(R.id.radioButtonMovies)
        seriesButton = requireView().findViewById(R.id.radioButtonSeries)

        allButton.setOnClickListener(this)
        sportsButton.setOnClickListener(this)
        moviesButton.setOnClickListener(this)
        seriesButton.setOnClickListener(this)

        categoriesRecyclerView = requireView().findViewById(R.id.categoriesRecyclerView)
        categoriesRecyclerView.setHasFixedSize(false)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        CreateCategoriesRecycleView().create(categoriesRecyclerView, "all")
    }

    override fun onClick(view: View)
    {
        val activeButon:ToggleButton = requireView().findViewById(view.id)

        allButton.setChecked(allButton == activeButon)
        sportsButton.setChecked(sportsButton == activeButon)
        moviesButton.setChecked(moviesButton == activeButon)
        seriesButton.setChecked(seriesButton == activeButon)

        if(allButton.isChecked) {
            active = "all"
            CreateCategoriesRecycleView().create(categoriesRecyclerView, active)
        }
        if(sportsButton.isChecked) {
            active = "sports"
            CreateCategoriesRecycleView().create(categoriesRecyclerView, active)
        }
        if(moviesButton.isChecked) {
            active = "movies"
            CreateCategoriesRecycleView().create(categoriesRecyclerView, active)
        }
        if(seriesButton.isChecked) {
            active = "series"
            CreateCategoriesRecycleView().create(categoriesRecyclerView, "series")
        }
    }
}