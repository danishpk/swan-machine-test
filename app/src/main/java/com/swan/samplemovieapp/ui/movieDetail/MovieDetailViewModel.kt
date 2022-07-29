package com.swan.samplemovieapp.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.swan.samplemovieapp.data.api.Api
import com.swan.samplemovieapp.data.models.Movie
import com.swan.samplemovieapp.data.models.response.Resource
import com.swan.samplemovieapp.ui.base.BaseViewModel

class MovieDetailViewModel(state: SavedStateHandle) : BaseViewModel() {

    private val movieId: Int? = state.get<Int>("movieId")

    private val _movie = MutableLiveData<Resource<Movie>>()
    val movie: LiveData<Resource<Movie>> = _movie

    init {
        fetchMovieDetails()
    }

    private fun fetchMovieDetails() {
        fetchData(_movie) {
            Api.movieApi.getMovieDetail(movieId = movieId)
        }
    }
    fun refresh() {
        fetchMovieDetails()
    }

}

