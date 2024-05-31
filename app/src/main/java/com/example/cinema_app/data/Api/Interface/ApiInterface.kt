package com.example.cinema_app.data.Api.Interface

import com.example.cinema_app.data.Api.DataClasses.BasketballEvent.BasketballResponse
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.FootballResponse
import com.example.cinema_app.data.Api.DataClasses.HockeyEvent.HockeyResponse
import com.example.cinema_app.data.Api.DataClasses.Films.MoviesResponse
import com.example.cinema_app.data.Api.DataClasses.VolleyballEvent.VolleyballResponse
import retrofit2.http.GET
import retrofit2.http.Headers

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
                "&selectFields=backdrop" +
                "&selectFields=persons" +
                "&selectFields=countries" +
                "&selectFields=ageRating" +
                "&selectFields=premiere" +
                "&notNullFields=movieLength" +
                "&notNullFields=name" +
                "&notNullFields=description" +
                "&notNullFields=rating.imdb" +
                "&notNullFields=rating.imdb" +
                "&notNullFields=poster.url" +
                "&notNullFields=genres.name" +
                "&selectFields=year" +
                "&notNullFields=year" +
                "&notNullFields=videos.trailers.url" +
                "&notNullFields=backdrop.url" +
                "&notNullFields=persons.photo" +
                "&notNullFields=persons.name" +
                "&notNullFields=persons.profession"+

                "&notNullFields=countries.name" +
                "&notNullFields=ageRating" +
                "&notNullFields=premiere.world" +
                "&notNullFields=premiere.russia" +
                "&notNullFields=audience.country"+
                "&token=0HR2NYV-SW149QR-H2NX838-05J2J5N"
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
                "&selectFields=year" +
                "&year=2024" +
                "&notNullFields=name" +
                "&notNullFields=description" +
                "&notNullFields=poster.url" +
                "&notNullFields=rating.imdb" +
                "&notNullFields=year" +
                "&token=0HR2NYV-SW149QR-H2NX838-05J2J5N"
    )
    suspend fun getMovieNew(): MoviesResponse

    @Headers(
        "x-rapidapi-key: 942901e89024c5b511096352d1a5b61a",
        "x-rapidapi-host: v3.football.api-sports.io"
    )
    @GET("fixtures?league=235&season=2023")
    suspend fun getFootballResponse(): FootballResponse

    @Headers(
        "x-rapidapi-key: 942901e89024c5b511096352d1a5b61a",
        "x-rapidapi-host: v1.basketball.api-sports.io"
    )
    @GET("games?league=13&season=2023")
    suspend fun getBasketballResponse(): BasketballResponse

    @Headers(
        "x-rapidapi-key: 942901e89024c5b511096352d1a5b61a",
        "x-rapidapi-host: v1.basketball.api-sports.io"
    )
    @GET("games?league=132&season=2023")
    suspend fun getVolleyballResponse(): VolleyballResponse

    @Headers(
        "x-rapidapi-key: 942901e89024c5b511096352d1a5b61a",
        "x-rapidapi-host: v1.basketball.api-sports.io"
    )
    @GET("games?league=35&season=2023")
    suspend fun getHockeyResponse(): HockeyResponse
}
