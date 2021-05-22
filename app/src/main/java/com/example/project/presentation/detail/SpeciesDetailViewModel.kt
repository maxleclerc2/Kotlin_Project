package com.example.project.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.SpeciesDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpeciesDetailViewModel : ViewModel() {
    val species : MutableLiveData<SpeciesDetailModel> = MutableLiveData()

    init {
        // null
    }

    fun callApi(uid : String) {
        species.value = SpeciesDetailLoader

        Singletons.starWarsApi.getSpeciesDetail(uid).enqueue(object : Callback<SpeciesDetailResponse> {
            override fun onFailure(call: Call<SpeciesDetailResponse>, t: Throwable) {
                species.value = SpeciesDetailError
            }

            override fun onResponse(call: Call<SpeciesDetailResponse>, response: Response<SpeciesDetailResponse>) {
                if(response.isSuccessful && response.body() != null) {
                    val speciesResponse = response.body()!!
                    species.value = SpeciesDetailSuccess(speciesResponse.result)
                }
            }

        })
    }
}