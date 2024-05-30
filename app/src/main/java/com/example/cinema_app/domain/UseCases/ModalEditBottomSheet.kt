package com.example.cinema_app.domain.UseCases

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
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

class ModalEditBottomSheet(
    private val currentItem: PlaylistWithFilms,
    private val updateListener: PlaylistUpdateListener
) : BottomSheetDialogFragment() {

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

        EditBtn.setOnClickListener { showEditDialog() }
        DeleteBtn.setOnClickListener { showDeleteConfirmationDialog() }

        return view
    }

    private fun showEditDialog() {
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.dialog_edit_playlist, null)
        val playlistNameEditText = dialogView.findViewById<EditText>(R.id.playlistNameEditText)
        val playlistDescriptionEditText = dialogView.findViewById<EditText>(R.id.playlistDescriptionEditText)

        // Заполняем EditText текущими значениями плейлиста
        playlistNameEditText.setText(currentItem.playlist.name)
        playlistDescriptionEditText.setText(currentItem.playlist.description)

        AlertDialog.Builder(requireContext(), R.style.TransparentDialog) // Применяем стиль TransparentDialog
            .setTitle("Редактировать плейлист")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { dialog, which ->
                val newName = playlistNameEditText.text.toString()
                val newDescription = playlistDescriptionEditText.text.toString()

                // Показываем тост
                Toast.makeText(requireContext(), "Редактирование плейлиста", Toast.LENGTH_SHORT).show()

                updatePlaylistInDatabase(newName, newDescription)
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun showDeleteConfirmationDialog() {
        val dialog = AlertDialog.Builder(requireContext(), R.style.TransparentDialog) // Применяем стиль TransparentDialog
            .setTitle("Удаление плейлиста")
            .setMessage("Вы уверены, что хотите удалить плейлист?")
            .setPositiveButton("Да") { dialog, which ->
                deletePlaylistFromDatabase()
            }
            .setNegativeButton("Нет", null)
            .show()

        // Получаем доступ к TextView в диалоге и меняем цвет текста и hint
        val messageTextView = dialog.findViewById<TextView>(android.R.id.message)
        messageTextView?.setTextColor(ContextCompat.getColor(requireContext(), R.color.silver))
        messageTextView?.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.silver_transparent))

    }


    private fun updatePlaylistInDatabase(name: String, description: String) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val playlistDao = MainDb.getDb(requireContext()).getPlaylistDao()
                val playlistId = currentItem.playlist.id

                if (playlistId != null) {
                    val updatedPlaylist = Playlist(
                        id = playlistId,
                        name = name,
                        description = description,
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

    private fun deletePlaylistFromDatabase() {
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

    companion object {
        const val TAG = "ModalEditBottomSheet"
    }
}
