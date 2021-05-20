package com.example.project.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.ElementListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VehicleListViewModel : ViewModel() {
    val vehicleList: MutableLiveData<ElementListModel> = MutableLiveData()

    init {

    }

    fun callApi(page: String) {
        vehicleList.value = ElementListLoader

        Singletons.starWarsApi.getVehicleList(page, "10").enqueue(object : Callback<ElementListResponse> {
            override fun onFailure(call: Call<ElementListResponse>, t: Throwable) {
                vehicleList.value = ElementListError
            }

            override fun onResponse(call: Call<ElementListResponse>, response: Response<ElementListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val elementResponse = response.body()!!
                    vehicleList.value = ElementListSuccess(elementResponse.results, elementResponse.previous, elementResponse.next)
                } else {
                    vehicleList.value = ElementListError
                }
            }

        })
    }
}