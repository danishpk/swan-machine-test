package com.swan.samplemovieapp.ui.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.swan.samplemovieapp.AppConfig
import com.swan.samplemovieapp.data.models.Movie

class MovieListViewModel : ViewModel() {

    val movies: LiveData<PagingData<Movie>> = Pager(
            config = PagingConfig(
                pageSize = AppConfig.PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { MoviesPagingSource() }
        ).liveData.cachedIn(viewModelScope)
    }