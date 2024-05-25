package com.example.cinema_app.data.DB.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cinema_app.data.DB.Entities.FavouriteFilm
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(currentFilm: FavouriteFilm)


    //Flow выдает данные при изменение/обновлении данных
    @Query("SELECT * FROM FavouriteTable")
    fun getAllPlayListsItems(): Flow<List<FavouriteFilm>>

}