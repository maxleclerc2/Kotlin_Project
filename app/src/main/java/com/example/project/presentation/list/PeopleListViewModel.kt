package com.example.project.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.ElementListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleListViewModel : ViewModel() {
    val peopleList: MutableLiveData<ElementListModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        peopleList.value = ElementListLoader

        Singletons.starWarsApi.getPeopleList("1","10").enqueue(object : Callback<ElementListResponse> {
            override fun onFailure(call: Call<ElementListResponse>, t: Throwable) {
                peopleList.value = ElementListError
            }

            override fun onResponse(call: Call<ElementListResponse>, response: Response<ElementListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val elementResponse = response.body()!!
                    peopleList.value = ElementListSuccess(elementResponse.results)
                }
            }

        })
    }
}