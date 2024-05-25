package com.example.cinema_app.data.DB.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinema_app.data.DB.Entities.Playlist
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {
    @Insert
    fun insertPlaylist(playlist: Playlist)

    @Query("SELECT * FROM PlaylistTable")
    fun getAllPlaylists(): Flow<List<Playlist>>

    @Query("DELETE FROM PlaylistTable WHERE id = :id")
    fun deletePlaylist(id: Int)

    @Query("DELETE FROM PlaylistTable")
    fun deleteAllPlaylists()


}