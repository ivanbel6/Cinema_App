package com.example.cinema_app.domain.UseCases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.data.PlayListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment() {
    private lateinit var okBtn: ImageView
    private lateinit var playListRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_bottom_sheet, container, false)
        okBtn = view.findViewById(R.id.ok_btn)
        playListRecyclerView = view.findViewById(R.id.PlayListRecycleView)
        var sliderList: List<String> = listOf(
            "Favoutir",
            "Another play list",
            "Another play list",
            "Another play list",
        )
        playListRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        playListRecyclerView.adapter = PlayListAdapter(sliderList)
        return view
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}