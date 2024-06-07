package com.example.cinema_app.presentation

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.Interface.ApiInterface
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

private lateinit var detectiveRecycleView: RecyclerView
private lateinit var familyRecycleView: RecyclerView
private lateinit var historyRecycleView: RecyclerView
private val scrollHandler = Handler()

class MovieFragment : Fragment() {
    private val viewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory(MainActivity.retrofit.create(ApiInterface::class.java))
    }

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



//        /**
//         * SLIDER ON THE TOP
//         */
//        topRecycleView = requireView().findViewById(R.id.sliderRecyclerView)
//        CreateTopSlider(a, genreRecyclerView).fillTopSliderFilms(requireContext(), topRecycleView, scrollHandler)
//        topRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * SLIDER POPULAR
//        */

        binding.vp2.setUserInputEnabled(true)
        popularRecycleView = requireView().findViewById(R.id.CustomRecycleView)
        CreateSlider(viewModel).fillFilms(popularRecycleView, requireContext(), "")
        popularRecycleView.addOnItemTouchListener(onTouchListener)
//
//        /**
//         * DRAMA SLIDER
//         */
//        dramaRecycleView = requireView().findViewById(R.id.dramaRecyclerView)
//        CreateSlider(viewModel).fillFilms(dramaRecycleView, requireContext(), "драма")
//        dramaRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//        * FIGHT SLIDER
//        */
//        fightRecycleView = requireView().findViewById(R.id.fightRecyclerView)
//        CreateSlider(viewModel).fillFilms(fightRecycleView, requireContext(), "боевик")
//        fightRecycleView.addOnItemTouchListener(onTouchListener)
//
//        /**
//         * COMEDY SLIDER
//         */
//        comedyRecycleView = requireView().findViewById(R.id.comedyRecyclerView)
//        CreateSlider(viewModel).fillFilms(comedyRecycleView, requireContext(), "комедия")
//        comedyRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * HORRORS SLIDER
//         */
//        horrorsRecycleView = requireView().findViewById(R.id.horrorsRecyclerView)
//        CreateSlider(viewModel).fillFilms(horrorsRecycleView, requireContext(), "ужасы")
//        horrorsRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * FANTASTIC  SLIDER
//         */
//        scienceFictionRecycleView = requireView().findViewById(R.id.scienceFictionRecyclerView)
//        CreateSlider(viewModel).fillFilms(scienceFictionRecycleView, requireContext(), "фантастика")
//        scienceFictionRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * CARTOONS SLIDER
//         */
//        cartoonsRecycleView = requireView().findViewById(R.id.cartoonsRecyclerView)
//        CreateSlider(viewModel).fillFilms(cartoonsRecycleView, requireContext(), "мультфильм")
//        cartoonsRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * ADVENTURE SLIDER
//         */
//        adventureRecycleView = requireView().findViewById(R.id.adventuresRecyclerView)
//        CreateSlider(viewModel).fillFilms(adventureRecycleView, requireContext(), "приключения")
//        adventureRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * ANIMATION SLIDER
//         */
//        animRecycleView= requireView().findViewById(R.id.animRecyclerView)
//        CreateSlider(viewModel).fillFilms(animRecycleView, requireContext(), "аниме")
//        animRecycleView.addOnItemTouchListener(onTouchListener)
//
//        /**
//         * DETECTIVE SLIDER
//         */
//        detectiveRecycleView = requireView().findViewById(R.id.detectiveRecyclerView)
//        CreateSlider(viewModel).fillFilms(detectiveRecycleView, requireContext(), "детектив")
//        detectiveRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * FAMILY SLIDER
//         */
//        familyRecycleView = requireView().findViewById(R.id.familyRecyclerView)
//        CreateSlider(viewModel).fillFilms(familyRecycleView, requireContext(), "семейный")
//        familyRecycleView.addOnItemTouchListener(onTouchListener)
//        /**
//         * HISTORY SLIDER
//         */
//        historyRecycleView = requireView().findViewById(R.id.historyRecyclerView)
//        CreateSlider(viewModel).fillFilms(historyRecycleView, requireContext(), "история")
//        historyRecycleView.addOnItemTouchListener(onTouchListener)
    }

    companion object{
        @JvmStatic
        fun newInstance() = MovieFragment()
    }
}