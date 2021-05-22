package com.example.project.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.VehicleDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VehicleDetailViewModel : ViewModel() {
    val vehicle : MutableLiveData<VehicleDetailModel> = MutableLiveData()

    init {
        // null
    }

    fun callApi(uid : String) {
        vehicle.value = VehicleDetailLoader

        Singletons.starWarsApi.getVehicleDetail(uid).enqueue(object : Callback<VehicleDetailResponse> {
            override fun onFailure(call: Call<VehicleDetailResponse>, t: Throwable) {
                vehicle.value = VehicleDetailError
            }

            override fun onResponse(call: Call<VehicleDetailResponse>, response: Response<VehicleDetailResponse>) {
                if(response.isSuccessful && response.body() != null) {
                    val vehicleResponse = response.body()!!
                    vehicle.value = VehicleDetailSuccess(vehicleResponse.result)
                }
            }

        })
    }
}