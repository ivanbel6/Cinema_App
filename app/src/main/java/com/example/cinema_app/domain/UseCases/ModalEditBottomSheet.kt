package com.example.cinema_app.domain.UseCases

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.cinema_app.R
import com.example.cinema_app.data.DB.Entities.Playlist
import com.example.cinema_app.data.DB.Entities.PlaylistWithFilms
import com.example.cinema_app.data.DB.MainDb
import com.example.cinema_app.data.Interfaces.PlaylistUpdateListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModalEditBottomSheet(val currentItem: PlaylistWithFilms, val updateListener: PlaylistUpdateListener) : BottomSheetDialogFragment() {

    private lateinit var EditBtn: LinearLayout
    private lateinit var DeleteBtn: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_edit_bottom_sheet, container, false)
        EditBtn = view.findViewById(R.id.edit_btn_bottom_sheet)
        DeleteBtn = view.findViewById(R.id.delete_btn_bottom_sheet)

        EditBtn.setOnClickListener {
            // Создаем кастомный диалог с двумя EditText
            val inflater = LayoutInflater.from(requireContext())
            val dialogView = inflater.inflate(R.layout.dialog_edit_playlist, null)
            val playlistNameEditText = dialogView.findViewById<EditText>(R.id.playlistNameEditText)
            val playlistDescriptionEditText = dialogView.findViewById<EditText>(R.id.playlistDescriptionEditText)

            // Заполняем EditText текущими значениями плейлиста
            playlistNameEditText.setText(currentItem.playlist.name)
            playlistDescriptionEditText.setText(currentItem.playlist.description)

            AlertDialog.Builder(requireContext())
                .setTitle("Редактировать плейлист")
                .setView(dialogView)
                .setPositiveButton("Сохранить") { dialog, which ->
                    val newName = playlistNameEditText.text.toString()
                    val newDescription = playlistDescriptionEditText.text.toString()

                    // Показываем тост
                    Toast.makeText(requireContext(), "Редактирование плейлиста", Toast.LENGTH_SHORT).show()

                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            val playlistDao = MainDb.getDb(requireContext()).getPlaylistDao()
                            val playlistId = currentItem.playlist.id

                            if (playlistId != null) {
                                // Обновляем плейлист
                                val updatedPlaylist = Playlist(
                                    id = playlistId,
                                    name = newName,
                                    description = newDescription,
                                    isSelected = currentItem.playlist.isSelected
                                )
                                playlistDao.updatePlaylist(updatedPlaylist)
                            }

                            withContext(Dispatchers.Main) {
                                updateListener.onPlaylistUpdated()
                            }
                        }
                    }
                }
                .setNegativeButton("Отмена", null)
                .show()
        }

        DeleteBtn.setOnClickListener {
            // Создаем AlertDialog для подтверждения удаления
            AlertDialog.Builder(requireContext())
                .setTitle("Удаление плейлиста")
                .setMessage("Вы уверены, что хотите удалить плейлист?")
                .setPositiveButton("Да") { dialog, which ->
                    // Если пользователь подтвердил удаление, показываем тост и удаляем плейлист
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            val playlistDao = MainDb.getDb(requireContext()).getPlaylistDao()
                            val playlistId = playlistDao.getPlaylistIdByName(currentItem.playlist.name)
                            playlistDao.deletePlaylist(playlistId)
                            withContext(Dispatchers.Main) {
                                updateListener.onPlaylistUpdated()
                            }
                        }
                    }
                }
                .setNegativeButton("Нет", null) // Если пользователь отказался, просто закрываем диалог
                .show()
        }

        return view
    }

    companion object {
        const val TAG = "ModalEditBottomSheet"
    }
}
