package com.example.project.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.FilmDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetailViewModel : ViewModel() {
    val film : MutableLiveData<FilmDetailModel> = MutableLiveData()

    init {
        //callApi(uid)
    }

    fun callApi(uid : String) {
        film.value = FilmDetailLoader

        Singletons.starWarsApi.getFilmDetail(uid).enqueue(object : Callback<FilmDetailResponse> {
            override fun onFailure(call: Call<FilmDetailResponse>, t: Throwable) {
                film.value = FilmDetailError
            }

            override fun onResponse(call: Call<FilmDetailResponse>, response: Response<FilmDetailResponse>) {
                if(response.isSuccessful && response.body() != null) {
                    val filmResponse = response.body()!!
                    film.value = FilmDetailSuccess(filmResponse.result)
                }
            }

        })
    }
}