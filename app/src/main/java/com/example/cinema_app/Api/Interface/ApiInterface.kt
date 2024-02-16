package com.example.cinema_app.Api.Interface


import com.example.cinema_app.Api.DataClasses.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("v1.4/movie?page=1&limit=249&selectFields=names&selectFields=description&selectFields=genres&selectFields=poster&selectFields=videos&notNullFields=&sortField=year&sortType=1&ageRating=0-18&genres.name=%D0%B4%D1%80%D0%B0%D0%BC%D0%B0&token=0HR2NYV-SW149QR-H2NX838-05J2J5N")
    suspend fun getMovies(): MoviesResponse
}