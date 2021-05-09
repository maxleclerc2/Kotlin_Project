package com.example.project.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.presentation.Singletons
import com.example.project.presentation.api.FilmDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetailFragment : Fragment() {

    private lateinit var textViewTitle: TextView
    private lateinit var textViewTitleValue: TextView
    private lateinit var textViewProducer: TextView
    private lateinit var textViewProducerValue: TextView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private val viewModel: FilmDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewTitle = view.findViewById(R.id.film_detail_title)
        textViewTitleValue = view.findViewById(R.id.film_detail_title_value)
        textViewProducer = view.findViewById(R.id.film_detail_producer)
        textViewProducerValue = view.findViewById(R.id.film_detail_producer_value)

        loader = view.findViewById(R.id.film_detail_loader)
        textViewError = view.findViewById(R.id.film_detail_error)

        val uid = arguments?.getString("uid") ?: ""

        viewModel.callApi(uid)
        viewModel.film.observe(viewLifecycleOwner, Observer { filmDetailModel ->
            //loader.isVisible = filmDetailModel is FilmDetailLoader
            //textViewError.isVisible = filmDetailModel is FilmDetailError

            when(filmDetailModel) {
                is FilmDetailSuccess -> {
                    textViewTitle.isVisible = true
                    textViewTitleValue.isVisible = true
                    textViewProducer.isVisible = true
                    textViewProducerValue.isVisible = true

                    textViewTitleValue.text = filmDetailModel.film.properties.title
                    textViewProducerValue.text = filmDetailModel.film.properties.producer

                    loader.isVisible = false
                    textViewError.isVisible = false
                }

                FilmDetailError -> {
                    textViewTitle.isVisible = false
                    textViewTitleValue.isVisible = false
                    textViewProducer.isVisible = false
                    textViewProducerValue.isVisible = false

                    loader.isVisible = false
                    textViewError.isVisible = true
                }

                FilmDetailLoader -> {
                    textViewTitle.isVisible = false
                    textViewTitleValue.isVisible = false
                    textViewProducer.isVisible = false
                    textViewProducerValue.isVisible = false

                    loader.isVisible = true
                    textViewError.isVisible = false
                }
            }
        })

        view.findViewById<Button>(R.id.button_return).setOnClickListener {
            findNavController().navigate(R.id.action_FilmDetailFragment_to_FilmListFragment)
        }
        //callApi(uid)
    }

    private fun callApi(uid : String) {
        Singletons.starWarsApi.getFilmDetail(uid).enqueue(object : Callback<FilmDetailResponse> {
            override fun onFailure(call: Call<FilmDetailResponse>, t: Throwable) {
                //TODO("Not yet implemented")
                println("test")
            }

            override fun onResponse(call: Call<FilmDetailResponse>, response: Response<FilmDetailResponse>) {
                if(response.isSuccessful && response.body() != null) {
                    println("test 2")
                    textViewTitleValue.text = response.body()!!.result.properties.title
                }
            }

        })
    }
}