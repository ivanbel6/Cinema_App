package com.example.cinema_app.data.DB.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cinema_app.data.DB.Entities.FavouriteFilm

import com.example.cinema_app.data.DB.Entities.Playlist
import com.example.cinema_app.data.DB.Entities.PlaylistWithFilms
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    @Insert
    fun insertPlaylist(playlist: Playlist)

    @Query("SELECT * FROM PlaylistTable")
    fun getAllPlaylists(): Flow<List<Playlist>>

    @Query("DELETE FROM PlaylistTable WHERE id = :id")
    fun deletePlaylist(id: Long?)

    @Query("DELETE FROM PlaylistTable")
    fun deleteAllPlaylists()

    @Query("SELECT id FROM PlaylistTable WHERE name = :name")
    fun getPlaylistIdByName(name: String): Long?

    @Insert
    fun insertFilmToPlaylist(film: FavouriteFilm)

    @Query("SELECT * FROM PlaylistTable")
    fun getPlaylistsWithFilms(): List<PlaylistWithFilms>

    @Query("SELECT * FROM PlaylistTable WHERE name = :name")
    fun getPlaylistWithFilmsByName(name: String): PlaylistWithFilms

    @Update
    fun updatePlaylist(playlist: Playlist)


}



