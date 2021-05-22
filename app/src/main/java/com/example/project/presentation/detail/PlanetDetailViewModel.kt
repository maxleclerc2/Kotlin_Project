package com.example.project.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.PlanetDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlanetDetailViewModel : ViewModel() {
    val planet : MutableLiveData<PlanetDetailModel> = MutableLiveData()

    init {
        // null
    }

    fun callApi(uid : String) {
        planet.value = PlanetDetailLoader

        Singletons.starWarsApi.getPlanetDetail(uid).enqueue(object : Callback<PlanetDetailResponse> {
            override fun onFailure(call: Call<PlanetDetailResponse>, t: Throwable) {
                planet.value = PlanetDetailError
            }

            override fun onResponse(call: Call<PlanetDetailResponse>, response: Response<PlanetDetailResponse>) {
                if(response.isSuccessful && response.body() != null) {
                    val planetResponse = response.body()!!
                    planet.value = PlanetDetailSuccess(planetResponse.result)
                }
            }

        })
    }
}