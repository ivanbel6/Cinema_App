package com.example.cinema_app.data

class Categories {
    companion object {
        private val moviesSeriesCategories: List<String> = mutableListOf(
            "драма",
            "боевик",
            "комедия",
            "ужасы",
            "научная фантастика",
            "мультфильмы",
            "приключения",
            "аниме",
            "детектив",
            "семейное",
            "историческое"
        )
        private val sportCategories: List<String> = listOf(
            "футбол",
            "баскетбол",
            "воллейбол",
            "хоккей"
        )

        fun getAllCategories(): List<String> {
            return moviesSeriesCategories + sportCategories
        }

        fun getMoviesSeriesCategories(): List<String> {
            return moviesSeriesCategories
        }

        fun getSportsCategories(): List<String> {
            return sportCategories
        }
    }
}