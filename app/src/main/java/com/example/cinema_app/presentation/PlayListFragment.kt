package com.example.cinema_app.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema_app.R
import com.example.cinema_app.data.DB.Entities.Playlist
import com.example.cinema_app.data.DB.MainDb
import com.example.cinema_app.data.Interfaces.PlaylistUpdateListener
import com.example.cinema_app.data.adapters.PlayListAdapter_2
import com.example.cinema_app.databinding.FragmentPlayListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PlayListFragment : Fragment(), PlaylistUpdateListener {
    private var _binding: FragmentPlayListBinding? = null
    private val binding get() = _binding!!
    private lateinit var playlistAdapter: PlayListAdapter_2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createPlayList.setOnClickListener{
            showCreatePlaylistDialog()
        }
        binding.appBarLayout.setNavigationOnClickListener {
            val profileFragment = ProfileFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_login, profileFragment)
                .commit()
        }

        loadData()
    }

    private fun loadData() {
        val playlistDao = MainDb.getDb(requireContext()).getPlaylistDao()
        lifecycleScope.launch {
            val playlist = withContext(Dispatchers.IO) {
                playlistDao.getPlaylistsWithFilms()
            }

            if (binding.PlayListRecycleView.adapter == null) {
                playlistAdapter = PlayListAdapter_2(playlist, this@PlayListFragment)
                binding.PlayListRecycleView.apply {
                    setHasFixedSize(false)
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = playlistAdapter
                }
            } else {
                playlistAdapter.updateData(playlist)
            }
        }
    }

    private fun showCreatePlaylistDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit_playlist, null)
        val builder = AlertDialog.Builder(requireContext(), R.style.TransparentDialog) // Apply the custom theme here
            .setView(dialogView)
            .setPositiveButton("Create") { dialog, which ->
                val title = dialogView.findViewById<EditText>(R.id.playlistNameEditText).text.toString()
                val description = dialogView.findViewById<EditText>(R.id.playlistDescriptionEditText).text.toString()
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        val playlistDao = MainDb.getDb(requireContext()).getPlaylistDao()
                        playlistDao.insertPlaylist(
                            Playlist(name = title, description = description)
                        )
                    }
                    loadData()
                }
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setTitle("Create Playlist")

        val alertDialog = builder.create()

        // Установка ширины
        alertDialog.setOnShowListener {
            val window = alertDialog.window
            window?.setLayout(
                (resources.displayMetrics.widthPixels * 0.8).toInt(), // 90% of screen width
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        alertDialog.show()
    }
    override fun onPlaylistUpdated() {
        loadData()
    }
}