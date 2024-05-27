package com.example.cinema_app.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema_app.data.DB.Entities.Playlist
import com.example.cinema_app.data.DB.MainDb
import com.example.cinema_app.data.PlayListAdapter
import com.example.cinema_app.data.PlayListAdapter_2
import com.example.cinema_app.databinding.FragmentPlayListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PlayListFragment : Fragment() {
    private var _binding: FragmentPlayListBinding? = null // Привязка для фрагмента
    private val binding get() = _binding!!
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
        val playlistDao = MainDb.getDb(requireContext()).getPlaylistDao()


//        lifecycleScope.launchWhenCreated {
//            playlistDao.getAllPlaylists().collect { playlists ->
//                // Обновление списка адаптера
//                Log.v("Test_db_", playlists.toString())
//                playListRecyclerView.layoutManager =
//                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//                playListRecyclerView.adapter = PlayListAdapter(playlists)
//            }
//        }


        lifecycleScope.launch {
            val playlist = withContext(Dispatchers.IO) {
                // Выполните длительные операции здесь, например, получение данных из базы данных
                playlistDao.getPlaylistsWithFilms()
            }

            // После выполнения длительных операций обновите пользовательский интерфейс в основном потоке
            binding.PlayListRecycleView.apply {
                setHasFixedSize(false)
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = PlayListAdapter_2(playlist)
            }
            Log.v("Test_Play_list_", playlist.toString())
        }


    }


}