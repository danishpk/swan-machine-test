package com.swan.samplemovieapp.data.api
import com.swan.samplemovieapp.AppConfig
import com.swan.samplemovieapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object OkHttpClientFactory {
    fun createClient(): OkHttpClient {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            client.addInterceptor(interceptor)
        }

        client.addInterceptor { chain ->
            val url = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", AppConfig.API_KEY)
                .build()
            val builder = chain.request().newBuilder().url(url)
            return@addInterceptor chain.proceed(builder.build())
        }
        return client.build()
    }
}