package com.example.cinema_app.presentation

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.domain.UseCases.CreateTopSlider
private val scrollHandler = Handler()
private lateinit var topRecycleView: RecyclerView
class MovieFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val a = requireView().findViewById<LinearLayout>(R.id.indicator_lay)
        val genreRecyclerView = requireView().findViewById<RecyclerView>(R.id.GenresTopRecycleView)
        topRecycleView = requireView().findViewById(R.id.sliderRecyclerView)
        CreateTopSlider(this,a,genreRecyclerView).fillTopSlider(requireContext(), topRecycleView, scrollHandler)
        val background_image = requireView().findViewById<ImageView>(R.id.background_image)
        background_image.scaleType = ImageView.ScaleType.CENTER_CROP
    }


}