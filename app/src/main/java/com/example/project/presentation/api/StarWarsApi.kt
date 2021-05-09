package com.example.project.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {
    @GET("films")
    fun getFilmList(): Call<FilmListResponse>

    @GET("films/{uid}")
    fun getFilmDetail(@Path("uid") uid: String): Call<FilmDetailResponse>

    @GET("people")
    fun getPeopleList(@Query("page") page: String, @Query("limit") limit: String): Call<ElementListResponse>

    @GET("people/{uid}")
    fun getPeopleDetail(@Path("uid") uid: String): Call<PeopleDetailResponse>

    @GET("planets")
    fun getPlanetList(@Query("page") page: String, @Query("limit") limit: String): Call<ElementListResponse>

    @GET("planets/{uid}")
    fun getPlanetDetail(@Path("uid") uid: String): Call<PlanetDetailResponse>

    @GET("species")
    fun getSpeciesList(@Query("page") page: String, @Query("limit") limit: String): Call<ElementListResponse>

    @GET("species/{uid}")
    fun getSpeciesDetail(@Path("uid") uid: String): Call<SpeciesDetailResponse>

    @GET("starships")
    fun getStarshipList(@Query("page") page: String, @Query("limit") limit: String): Call<ElementListResponse>

    @GET("starships/{uid}")
    fun getStarshipDetail(@Path("uid") uid: String): Call<StarshipDetailResponse>

    @GET("vehicles")
    fun getVehicleList(@Query("page") page: String, @Query("limit") limit: String): Call<ElementListResponse>

    @GET("vehicles/{uid}")
    fun getVehicleDetail(@Path("uid") uid: String): Call<VehicleDetailResponse>
}