package com.example.project.presentation

import com.example.project.presentation.StarWarsApplication.Companion.context
import com.example.project.presentation.api.StarWarsApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singletons {
    companion object {
        var cache: Cache = Cache(File(context?.cacheDir, "responses"), 10 * 1024 * 1024)

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
                .build()

        val starWarsApi: StarWarsApi = Retrofit.Builder()
            .baseUrl("https://www.swapi.tech/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(StarWarsApi::class.java)
    }
}