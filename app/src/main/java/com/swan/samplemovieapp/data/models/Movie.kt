package com.swan.samplemovieapp.data.models

import com.google.gson.annotations.SerializedName
import com.swan.samplemovieapp.AppConfig
import com.swan.samplemovieapp.utils.FormatUtils

data class Movie(
    @SerializedName("id")
    val id: Int,

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

    @SerializedName("release_date")
    val strReleaseDate: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?

) {
    val imageUrl: String get() {
       return AppConfig.IMAGE_URL + posterPath
    }

    val releaseDate: String? get() = strReleaseDate?.let { FormatUtils.formattedDate(it) }
}
