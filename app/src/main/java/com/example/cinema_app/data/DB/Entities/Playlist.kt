package com.example.cinema_app.data.DB.Entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "PlaylistTable")
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean = false
)

@Entity(tableName = "FavouriteTable")
data class FavouriteFilm(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "playlistId")
    var playlistId: Long?,
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
    val ageRating: Int
)
data class PlaylistWithFilms(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "id",
        entityColumn = "playlistId"
    )
    val films: List<FavouriteFilm>
)
