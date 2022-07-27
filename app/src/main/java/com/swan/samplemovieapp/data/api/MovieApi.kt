package com.swan.samplemovieapp.data.api
import com.swan.samplemovieapp.data.models.Movie
import com.swan.samplemovieapp.data.models.response.PaginatedResponse
import retrofit2.http.*

interface MovieApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int?
    ): PaginatedResponse<Movie>

}