package com.example.cinema_app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.domain.UseCases.CreateBasketballSlider
import com.example.cinema_app.domain.UseCases.CreateFootballSlider
import com.example.cinema_app.domain.UseCases.CreateHockeySlider
import com.example.cinema_app.domain.UseCases.CreateVolleyballSlider

private lateinit var footballRecycleView: RecyclerView
private lateinit var basketballRecycleView: RecyclerView
private lateinit var volleyballRecycleView: RecyclerView
private lateinit var hockeyRecycleView: RecyclerView

class SportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sport, container, false)
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

        //Football Recycle View
//        footballRecycleView = requireView().findViewById(R.id.footballRecycleView)
//        footballRecycleView.setHasFixedSize(false)
//        footballRecycleView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        CreateFootballSlider().fill(footballRecycleView, requireContext())
//        footballRecycleView.addOnItemTouchListener(onTouchListener)

        //Basketball Recycle View
//        basketballRecycleView = requireView().findViewById(R.id.basketballRecycleView)
//        basketballRecycleView.setHasFixedSize(false)
//        basketballRecycleView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        CreateBasketballSlider().fill(basketballRecycleView, requireContext())
//        basketballRecycleView.addOnItemTouchListener(onTouchListener)

        //Volleyball Recycle View
//        volleyballRecycleView = requireView().findViewById(R.id.volleyballRecycleView)
//        volleyballRecycleView.setHasFixedSize(false)
//        volleyballRecycleView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        CreateVolleyballSlider().fill(volleyballRecycleView, requireContext())
//        volleyballRecycleView.addOnItemTouchListener(onTouchListener)

        //Hockey Recycle View
        hockeyRecycleView = requireView().findViewById(R.id.hockeyRecycleView)
        hockeyRecycleView.setHasFixedSize(false)
        hockeyRecycleView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        CreateHockeySlider().fill(hockeyRecycleView, requireContext())
        hockeyRecycleView.addOnItemTouchListener(onTouchListener)
    }

    companion object{
        @JvmStatic
        fun newInstance() = SportFragment()
    }
}