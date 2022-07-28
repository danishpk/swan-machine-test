package com.swan.samplemovieapp.data.api

import com.swan.samplemovieapp.AppConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private const val BASE_URL = AppConfig.API_BASE_URL

    private val client by lazy { OkHttpClientFactory.createClient() }

    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }
    val movieApi: MovieApi by lazy { retrofit.create(MovieApi::class.java) }

}