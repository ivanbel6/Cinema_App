package com.example.cinema_app.domain.UseCases

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_app.R
import com.example.cinema_app.data.DB.Entities.Playlist
import com.example.cinema_app.data.DB.MainDb
import com.example.cinema_app.data.PlayListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModalBottomSheet : BottomSheetDialogFragment() {
    private lateinit var okBtn: ImageView
    private lateinit var createPlayListBtn: ImageView
    private lateinit var playListRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_bottom_sheet, container, false)
        okBtn = view.findViewById(R.id.ok_btn)
        createPlayListBtn = view.findViewById(R.id.CreatePlayListBtn)
        playListRecyclerView = view.findViewById(R.id.PlayListRecycleView)

        val playlistDao = MainDb.getDb(requireContext()).getPlaylistDao()
        lifecycleScope.launchWhenCreated {
            playlistDao.getAllPlaylists().collect { playlists ->
                // Обновление списка адаптера
                Log.v("Test_db_", playlists.toString())
                playListRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                playListRecyclerView.adapter = PlayListAdapter(playlists)
            }
        }

        okBtn.setOnClickListener {
            // Вставка нового плейлиста

        }
//        lifecycleScope.launch {
//            withContext(Dispatchers.IO) {
//                playlistDao.deleteAllPlaylists()
//            }
//        }
        createPlayListBtn.setOnClickListener {
            initializeDialog()
        }
        return view
    }

    fun initializeDialog() {
        val builder = MaterialAlertDialogBuilder(requireContext(),R.style.MyTitle_MaterialAlertDialog_MaterialComponents_Title_Text)
        builder
            .setTitle(resources.getString(R.string.title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setBackground(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bottom_sheet_background
                )
            )

        // Создание полей ввода текста
        val input1 = EditText(requireContext())
        input1.setTextColor(Color.WHITE) // Установка цвета текста
        input1.hint = "Название"
        input1.hint = SpannableString(input1.hint).apply {
            setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.transparent50)), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        input1.layoutParams = LinearLayout.LayoutParams(
            800, // Ширина 100 dp
            LinearLayout.LayoutParams.WRAP_CONTENT // Автоматическая высота
        ).apply {
            gravity = Gravity.CENTER_HORIZONTAL // Центрирование по горизонтали
        }

        val input2 = EditText(requireContext())
        input2.setTextColor(Color.WHITE) // Установка цвета текста
        input2.hint = "Описание"
        input2.hint = SpannableString(input2.hint).apply {
            setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.transparent50)), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        input2.layoutParams = LinearLayout.LayoutParams(
            800, // Ширина 100 dp
            LinearLayout.LayoutParams.WRAP_CONTENT // Автоматическая высота
        ).apply {
            gravity = Gravity.CENTER_HORIZONTAL // Центрирование по горизонтали
        }





        // Добавление полей ввода текста в диалог
        builder.setView(LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            addView(input1)
            addView(input2)
        })
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                // Обработка нажатия на положительную кнопку
                val name = input1.text.toString()
                val description = input2.text.toString()
                val playlistDao = MainDb.getDb(requireContext()).getPlaylistDao()
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        playlistDao.insertPlaylist(
                            Playlist(name = name, description = description)
                        )
                    }
                }
                lifecycleScope.launchWhenCreated {
                    playlistDao.getAllPlaylists().collect { playlists ->
                        // Обновление списка адаптера
                        Log.v("Test_db_", playlists.toString())
                        playListRecyclerView.layoutManager =
                            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        playListRecyclerView.adapter = PlayListAdapter(playlists)
                    }
                }
            }
            .show()
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}