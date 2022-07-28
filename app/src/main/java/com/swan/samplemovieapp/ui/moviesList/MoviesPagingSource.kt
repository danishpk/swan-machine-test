package com.swan.samplemovieapp.ui.moviesList

import androidx.paging.PagingState
import com.swan.samplemovieapp.data.api.Api
import com.swan.samplemovieapp.data.models.Movie
import com.swan.samplemovieapp.data.models.response.PaginatedResponse
import com.swan.samplemovieapp.ui.base.BasePagingSource

class MoviesPagingSource : BasePagingSource<Movie>() {
    override suspend fun load(page: Int, limit: Int):PaginatedResponse<Movie> {
        return Api.movieApi.getTopRatedMovies(page = page)
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}
