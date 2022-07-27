package com.swan.samplemovieapp

import android.app.Application

class SampleMovieApp : Application() {

    companion object {
        private lateinit var instance: SampleMovieApp

        fun get() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}