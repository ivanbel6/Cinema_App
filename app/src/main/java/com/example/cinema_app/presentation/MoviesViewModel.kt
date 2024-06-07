package com.example.cinema_app.presentation

import androidx.lifecycle.*
import com.example.cinema_app.data.Api.DataClasses.Films.MoviesResponse
import com.example.cinema_app.data.Api.Interface.ApiInterface
import kotlinx.coroutines.launch

class MoviesViewModel(private val apiInterface: ApiInterface) : ViewModel() {

    private val _movies = MutableLiveData<MoviesResponse>()
    val movies: LiveData<MoviesResponse> get() = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = MoviesRepository(apiInterface).getMovies()
                _movies.postValue(response)
            } catch (e: Exception) {
                //ловим ошибку
            }
        }
    }
}

class MoviesViewModelFactory(private val apiInterface: ApiInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(apiInterface) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
