package com.example.project.presentation

import com.example.project.presentation.StarWarsApplication.Companion.context
import com.example.project.presentation.api.StarWarsApi
import com.ncornette.cache.OkCacheControl
import com.ncornette.cache.OkCacheControl.NetworkMonitor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class Singletons {
    companion object {
        //var cache: Cache = Cache(File(context?.cacheDir, "swapi_responses"), 10 * 1024 * 1024)

        var cacheSize = 10 * 1024 * 1024 // 10 MB
        var cache: Cache = Cache(context?.cacheDir, cacheSize.toLong())

        private val okHttpClient: OkHttpClient = OkCacheControl.on(OkHttpClient().newBuilder())
                .overrideServerCachePolicy(15, TimeUnit.MINUTES)
                //.forceCacheWhenOffline(networkMonitor)
                .apply() // return to the OkHttpClient.Builder instance
                //.addInterceptor(provideHttpLoggingInterceptor())
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