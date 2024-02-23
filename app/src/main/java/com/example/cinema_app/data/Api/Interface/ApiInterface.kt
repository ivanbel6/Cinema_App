package com.example.cinema_app.data.Api.Interface


import com.example.cinema_app.data.Api.DataClasses.MoviesResponse
import retrofit2.http.GET

interface ApiInterface {
    @GET(
        "v1.4/movie?page=1&limit=249" +
                "&selectFields=videos" +
                "&selectFields=names" +
                "&selectFields=top10" +
                "&selectFields=description" +
                "&selectFields=rating" +
                "&selectFields=logo" +
                "&selectFields=poster" +
                "&selectFields=genres" +
                "&token=0HR2NYV-SW149QR-H2NX838-05J2J5N"
    )
    suspend fun getMovies(): MoviesResponse


    @GET(
        "v1.4/movie?page=1" +
                "&limit=249&selectFields=videos" +
                "&selectFields=names&selectFields=top10" +
                "&selectFields=description" +
                "&selectFields=rating" +
                "&selectFields=logo" +
                "&selectFields=poster" +
                "&selectFields=genres" +
                "&year=2024" +
                "&token=0HR2NYV-SW149QR-H2NX838-05J2J5N"
    )
    suspend fun getMovieNew(): MoviesResponse
}