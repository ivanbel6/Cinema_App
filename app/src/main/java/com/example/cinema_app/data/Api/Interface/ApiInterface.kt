package com.example.cinema_app.data.Api.Interface


import com.example.cinema_app.data.Api.DataClasses.MoviesResponse
import retrofit2.http.GET

interface ApiInterface {
    @GET(
        "v1.4/movie?page=1&limit=249" +
                "&selectFields=videos" +
                "&selectFields=name" +
                "&selectFields=description" +
                "&selectFields=rating" +
                "&selectFields=logo" +
                "&selectFields=poster" +
                "&selectFields=genres" +
                "&selectFields=movieLength" +
                "&notNullFields=movieLength"+
                "&notNullFields=name" +
                "&notNullFields=description" +
                "&notNullFields=rating.imdb" +
                "&notNullFields=rating.imdb" +
                "&notNullFields=poster.url" +
                "&notNullFields=genres.name"+
                "&selectFields=year" +
                "&notNullFields=year"+
                "&token=7ZJEK3M-V5G4HGK-N12ZF81-32G9Z2X"
    )
    suspend fun getMovies(): MoviesResponse


    @GET(
        "v1.4/movie?page=1" +
                "&limit=5" +
                "&selectFields=videos" +
                "&selectFields=name&selectFields=top10" +
                "&selectFields=description" +
                "&selectFields=rating" +
                "&selectFields=logo" +
                "&selectFields=poster" +
                "&selectFields=genres" +
                "&selectFields=year"+
                "&year=2024" +
                "&notNullFields=name" +
                "&notNullFields=description" +
                "&notNullFields=poster.url" +
                "&notNullFields=rating.imdb" +
                "&notNullFields=year" +
                "&token=7ZJEK3M-V5G4HGK-N12ZF81-32G9Z2X"
    )
    suspend fun getMovieNew(): MoviesResponse
}
