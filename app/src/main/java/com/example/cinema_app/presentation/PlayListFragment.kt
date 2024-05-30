package com.example.cinema_app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema_app.R
import com.example.cinema_app.data.DB.MainDb
import com.example.cinema_app.data.Interfaces.PlaylistUpdateListener
import com.example.cinema_app.data.PlayListAdapter_2
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

            playlistAdapter = PlayListAdapter_2(playlist, this@PlayListFragment)
            binding.PlayListRecycleView.apply {
                setHasFixedSize(false)
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = playlistAdapter
            }
        }
    }

    override fun onPlaylistUpdated() {
        loadData()
    }
}