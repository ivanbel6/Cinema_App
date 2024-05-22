package com.example.cinema_app.data.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinema_app.data.Api.DataClasses.Backdrop
import com.example.cinema_app.data.Api.DataClasses.Country
import com.example.cinema_app.data.Api.DataClasses.Person
import com.example.cinema_app.data.Api.DataClasses.Poster
import com.example.cinema_app.data.Api.DataClasses.Premiere
import com.example.cinema_app.data.Api.DataClasses.Video

@Entity(tableName = "FavouriteTable")
data class FavouriteFilm(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "PosterUrl")
    val PosterUrl: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "date")
    val date: Int,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "Genre")
    val Genre: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "backdrop")
    val backdropURL: String,
    @ColumnInfo(name = "ageRating")
    val ageRating: Int,
    )