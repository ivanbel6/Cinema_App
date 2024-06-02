package com.example.cinema_app.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.Films.CustomDataClass
import com.example.cinema_app.domain.UseCases.CreateCategoriesRecycleView
import com.example.cinema_app.domain.UseCases.CreateSearchRecycleViews
import com.example.cinema_app.domain.UseCases.CreateSlider
import com.example.cinema_app.domain.UseCases.CreateTopSlider
import com.example.cinema_app.domain.UseCases.SearchData

private lateinit var type:String
private lateinit var genres:ArrayList<String>

class SearchFragmentResult : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        type = arguments?.getString("type")!!
        genres = arguments?.getStringArrayList("genre")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button:ConstraintLayout = requireView().findViewById(R.id.backButton)

        button.setOnClickListener{
            val searchFragment = SearchFragment()

            parentFragmentManager.beginTransaction()
            .replace(R.id.container, searchFragment)
            .commit()
        }

        val moviesResycleView:RecyclerView = requireView().findViewById(R.id.moviesRecyclerView)
        val seriesResycleView:RecyclerView = requireView().findViewById(R.id.seriesRecyclerView)
        val sportsResycleView:RecyclerView = requireView().findViewById(R.id.sportsRecyclerView)

        if(type == "movies") {
           CreateSearchRecycleViews().create_recycle_view(moviesResycleView, type, genres, "")
        }
        if(type == "series") {
            CreateSearchRecycleViews().create_recycle_view(seriesResycleView, type, genres, "")
        }
        if(type == "sports") {
            CreateSearchRecycleViews().create_recycle_view(sportsResycleView, type, genres, "")
        }
    }


}