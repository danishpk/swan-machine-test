package com.swan.samplemovieapp.data.models.response

import com.google.gson.annotations.SerializedName

data class PaginatedResponse<T> (
    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<T>,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("total_results")
    val totalResults: Int?,

)