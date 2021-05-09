package com.example.project.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.FilmDetailResponse
import com.example.project.presentation.api.PeopleDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleDetailViewModel : ViewModel() {
    private lateinit var uid: String
    val people : MutableLiveData<PeopleDetailModel> = MutableLiveData()

    init {
        // null
    }

    fun callApi(uid : String) {
        people.value = PeopleDetailLoader

        Singletons.starWarsApi.getPeopleDetail(uid).enqueue(object : Callback<PeopleDetailResponse> {
            override fun onFailure(call: Call<PeopleDetailResponse>, t: Throwable) {
                people.value = PeopleDetailError
            }

            override fun onResponse(call: Call<PeopleDetailResponse>, response: Response<PeopleDetailResponse>) {
                if(response.isSuccessful && response.body() != null) {
                    val peopleResponse = response.body()!!
                    people.value = PeopleDetailSuccess(peopleResponse.result)
                }
            }

        })
    }
}