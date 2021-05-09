package com.example.project.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.FilmListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmListViewModel : ViewModel() {
    val filmList: MutableLiveData<FilmListModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        filmList.value = FilmListLoader

        Singletons.starWarsApi.getFilmList().enqueue(object : Callback<FilmListResponse> {
            override fun onFailure(call: Call<FilmListResponse>, t: Throwable) {
                filmList.value = FilmListError
            }

            override fun onResponse(call: Call<FilmListResponse>, response: Response<FilmListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val filmResponse = response.body()!!
                    filmList.value = FilmListSuccess(filmResponse.result)
                }
            }

        })
    }
}