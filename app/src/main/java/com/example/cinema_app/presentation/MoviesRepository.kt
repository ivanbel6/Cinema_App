package com.example.cinema_app.presentation

import com.example.cinema_app.data.Api.Interface.ApiInterface

class MoviesRepository(private val apiInterface: ApiInterface) {
    suspend fun getMovies() = apiInterface.getMovies()
}