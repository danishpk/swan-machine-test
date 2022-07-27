package com.swan.samplemovieapp.data.models

import com.google.gson.annotations.SerializedName
import com.swan.samplemovieapp.AppConfig

data class Movie(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?

) {
    val imageUrl = AppConfig.IMAGE_URL + posterPath
}
