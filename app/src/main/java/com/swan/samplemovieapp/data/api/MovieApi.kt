package com.swan.samplemovieapp.data.api
import com.swan.samplemovieapp.data.models.Movie
import com.swan.samplemovieapp.data.models.response.PaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int?,
    ): PaginatedResponse<Movie>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int?,
    ): Movie

}