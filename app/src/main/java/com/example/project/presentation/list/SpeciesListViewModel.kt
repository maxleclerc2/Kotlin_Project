package com.example.project.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.ElementListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpeciesListViewModel : ViewModel() {
    val speciesList: MutableLiveData<ElementListModel> = MutableLiveData()

    init {
        
    }

    fun callApi(page: String) {
        speciesList.value = ElementListLoader

        Singletons.starWarsApi.getSpeciesList(page, "10").enqueue(object : Callback<ElementListResponse> {
            override fun onFailure(call: Call<ElementListResponse>, t: Throwable) {
                speciesList.value = ElementListError
            }

            override fun onResponse(call: Call<ElementListResponse>, response: Response<ElementListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val elementResponse = response.body()!!
                    speciesList.value = ElementListSuccess(elementResponse.results, elementResponse.previous, elementResponse.next)
                } else {
                    speciesList.value = ElementListError
                }
            }

        })
    }
}