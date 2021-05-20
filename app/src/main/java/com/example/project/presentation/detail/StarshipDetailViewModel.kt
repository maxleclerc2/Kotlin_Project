package com.example.project.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.StarshipDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarshipDetailViewModel : ViewModel() {
    private lateinit var uid: String
    val starship : MutableLiveData<StarshipDetailModel> = MutableLiveData()

    init {
        // null
    }

    fun callApi(uid : String) {
        starship.value = StarshipDetailLoader

        Singletons.starWarsApi.getStarshipDetail(uid).enqueue(object : Callback<StarshipDetailResponse> {
            override fun onFailure(call: Call<StarshipDetailResponse>, t: Throwable) {
                starship.value = StarshipDetailError
            }

            override fun onResponse(call: Call<StarshipDetailResponse>, response: Response<StarshipDetailResponse>) {
                if(response.isSuccessful && response.body() != null) {
                    val starshipResponse = response.body()!!
                    starship.value = StarshipDetailSuccess(starshipResponse.result)
                }
            }

        })
    }
}