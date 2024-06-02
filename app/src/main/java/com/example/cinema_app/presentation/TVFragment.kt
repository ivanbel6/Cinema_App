package com.example.cinema_app.presentation

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.domain.UseCases.CreateSlider
import com.example.cinema_app.domain.UseCases.CreateTopSlider

private lateinit var popularRecycleView: RecyclerView
private lateinit var topRecycleView: RecyclerView
private lateinit var CurrentGenres:String
private lateinit var topRecycleViewGenres: RecyclerView
private lateinit var dramaRecycleView: RecyclerView
private lateinit var fightRecycleView: RecyclerView
private lateinit var comedyRecycleView: RecyclerView
private lateinit var horrorsRecycleView: RecyclerView
private lateinit var scienceFictionRecycleView: RecyclerView
private lateinit var cartoonsRecycleView: RecyclerView
private lateinit var adventureRecycleView: RecyclerView
private lateinit var animRecycleView: RecyclerView
private val scrollHandler = Handler()

class TVFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_t_v, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onTouchListener = object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        binding.vp2.isUserInputEnabled = false
                    }
                    MotionEvent.ACTION_UP -> {
                        binding.vp2.isUserInputEnabled = true
                    }
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        }
        val a = requireView().findViewById<LinearLayout>(R.id.indicator_lay)
        val genreRecyclerView = requireView().findViewById<RecyclerView>(R.id.GenresTopRecycleView)
        genreRecyclerView.addOnItemTouchListener(onTouchListener)

        /**
         * SLIDER ON THE TOP
         */
        topRecycleView = requireView().findViewById(R.id.sliderRecyclerView)
        CreateTopSlider(a, genreRecyclerView).fillTopSliderSeries(requireContext(), topRecycleView, scrollHandler)
        topRecycleView.addOnItemTouchListener(onTouchListener)

        /**
         * SLIDER POPULAR
        */
        binding.vp2.setUserInputEnabled(true)
        popularRecycleView = requireView().findViewById(R.id.CustomRecycleView)
        CreateSlider().fillSeries(popularRecycleView, requireContext(), "")
        popularRecycleView.addOnItemTouchListener(onTouchListener)

//        /**
//         * DRAMA SLIDER
//         */
//        dramaRecycleView = requireView().findViewById(R.id.dramaRecyclerView)
//        CreateSlider().fillSeries(dramaRecycleView, requireContext(), "драма")
//        dramaRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//        * FIGHT SLIDER
//        */
//        fightRecycleView = requireView().findViewById(R.id.fightRecyclerView)
//        CreateSlider().fillSeries(fightRecycleView, requireContext(), "боевик")
//        fightRecycleView.addOnItemTouchListener(onTouchListener)
//
//        /**
//         * COMEDY SLIDER
//         */
//        comedyRecycleView = requireView().findViewById(R.id.comedyRecyclerView)
//        CreateSlider().fillSeries(comedyRecycleView, requireContext(), "комедия")
//        comedyRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * HORRORS SLIDER
//         */
//        horrorsRecycleView = requireView().findViewById(R.id.horrorsRecyclerView)
//        CreateSlider().fillSeries(horrorsRecycleView, requireContext(), "ужасы")
//        horrorsRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * FANTASTIC  SLIDER
//         */
//        scienceFictionRecycleView = requireView().findViewById(R.id.scienceFictionRecyclerView)
//        CreateSlider().fillSeries(scienceFictionRecycleView, requireContext(), "фантастика")
//        scienceFictionRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * CARTOONS SLIDER
//         */
//        cartoonsRecycleView = requireView().findViewById(R.id.cartoonsRecyclerView)
//        CreateSlider().fillSeries(cartoonsRecycleView, requireContext(), "мультфильм")
//        cartoonsRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * ADVENTURE SLIDER
//         */
//        adventureRecycleView = requireView().findViewById(R.id.adventuresRecyclerView)
//        CreateSlider().fillSeries(adventureRecycleView, requireContext(), "приключения")
//        adventureRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * ANIMATION SLIDER
//         */
//        animRecycleView= requireView().findViewById(R.id.animRecyclerView)
//        CreateSlider().fillSeries(animRecycleView, requireContext(), "аниме")
//        animRecycleView.addOnItemTouchListener(onTouchListener)
    }

    companion object{
        @JvmStatic
        fun newInstance() = TVFragment()
    }
}