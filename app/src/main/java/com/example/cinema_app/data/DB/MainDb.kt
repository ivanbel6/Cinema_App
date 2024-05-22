package com.example.cinema_app.data.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteFilm::class], version = 1)
abstract class MainDb : RoomDatabase() {

    abstract fun getDao():Dao

    companion object {
    fun getDb(context: Context):MainDb{
        return Room.databaseBuilder(
            context.applicationContext,
            MainDb::class.java,
            "Favoutir_db.db"
        ).build()
    }
    }

}