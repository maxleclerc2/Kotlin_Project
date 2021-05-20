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
    private lateinit var textViewEpisodeId: TextView
    private lateinit var textViewEpisodeIdValue: TextView
    private lateinit var textViewRelease: TextView
    private lateinit var textViewReleaseValue: TextView
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
        textViewEpisodeId = view.findViewById(R.id.film_detail_episodeId)
        textViewEpisodeIdValue = view.findViewById(R.id.film_detail_episodeId_value)
        textViewRelease = view.findViewById(R.id.film_detail_release)
        textViewReleaseValue = view.findViewById(R.id.film_detail_release_value)

        loader = view.findViewById(R.id.film_detail_loader)
        textViewError = view.findViewById(R.id.film_detail_error)

        val uid = arguments?.getString("uid") ?: ""

        viewModel.callApi(uid)
        viewModel.film.observe(viewLifecycleOwner, Observer { filmDetailModel ->

            when(filmDetailModel) {
                is FilmDetailSuccess -> {
                    textViewTitle.isVisible = true
                    textViewTitleValue.isVisible = true
                    textViewProducer.isVisible = true
                    textViewProducerValue.isVisible = true
                    textViewEpisodeId.isVisible = true
                    textViewEpisodeIdValue.isVisible = true
                    textViewRelease.isVisible = true
                    textViewReleaseValue.isVisible = true

                    textViewTitleValue.text = filmDetailModel.film.properties.title
                    textViewProducerValue.text = filmDetailModel.film.properties.producer
                    textViewEpisodeIdValue.text = filmDetailModel.film.properties.episode_id.toString()
                    textViewReleaseValue.text = filmDetailModel.film.properties.release_date

                    loader.isVisible = false
                    textViewError.isVisible = false
                }

                FilmDetailError -> {
                    textViewTitle.isVisible = false
                    textViewTitleValue.isVisible = false
                    textViewProducer.isVisible = false
                    textViewProducerValue.isVisible = false
                    textViewEpisodeId.isVisible = false
                    textViewEpisodeIdValue.isVisible = false
                    textViewRelease.isVisible = false
                    textViewReleaseValue.isVisible = false

                    loader.isVisible = false
                    textViewError.isVisible = true
                }

                FilmDetailLoader -> {
                    textViewTitle.isVisible = false
                    textViewTitleValue.isVisible = false
                    textViewProducer.isVisible = false
                    textViewProducerValue.isVisible = false
                    textViewEpisodeId.isVisible = false
                    textViewEpisodeIdValue.isVisible = false
                    textViewRelease.isVisible = false
                    textViewReleaseValue.isVisible = false

                    loader.isVisible = true
                    textViewError.isVisible = false
                }
            }
        })

        view.findViewById<Button>(R.id.button_return).setOnClickListener {
            findNavController().navigate(R.id.action_FilmDetailFragment_to_FilmListFragment)
        }
    }
}