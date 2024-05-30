package com.example.cinema_app.data.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cinema_app.data.DB.Dao.Dao
import com.example.cinema_app.data.DB.Dao.PlaylistDao
import com.example.cinema_app.data.DB.Entities.FavouriteFilm
import com.example.cinema_app.data.DB.Entities.Playlist

@Database(entities = [FavouriteFilm::class, Playlist::class], version = 19)
abstract class MainDb : RoomDatabase() {

    abstract fun getDao(): Dao
    abstract fun getPlaylistDao(): PlaylistDao

    companion object {
    fun getDb(context: Context):MainDb{
        return Room.databaseBuilder(
            context.applicationContext,
            MainDb::class.java,
            "Favoutir_db.db"
        ).fallbackToDestructiveMigration().build()//При реальных данных это нужно изменить!
        //Удаляет данные при изменении БД (зато без миграции при разработке) - fallbackToDestructiveMigration()
    }
    }

}