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

class SpeciesDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewNameValue: TextView
    private lateinit var textViewClassification: TextView
    private lateinit var textViewClassificationValue: TextView
    private lateinit var textViewDesignation: TextView
    private lateinit var textViewDesignationValue: TextView
    private lateinit var textViewAverageHeight: TextView
    private lateinit var textViewAverageHeightValue: TextView
    private lateinit var textViewAverageLifespan: TextView
    private lateinit var textViewAverageLifespanValue: TextView
    private lateinit var textViewPopulation: TextView
    private lateinit var textViewPopulationValue: TextView

    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private val viewModel: SpeciesDetailViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_species_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.species_detail_name)
        textViewNameValue = view.findViewById(R.id.species_detail_name_value)
        textViewClassification = view.findViewById(R.id.species_detail_classification)
        textViewClassificationValue = view.findViewById(R.id.species_detail_classification_value)
        textViewDesignation = view.findViewById(R.id.species_detail_designation)
        textViewDesignationValue = view.findViewById(R.id.species_detail_designation_value)
        textViewAverageHeight = view.findViewById(R.id.species_detail_averageHeight)
        textViewAverageHeightValue = view.findViewById(R.id.species_detail_averageHeight_value)
        textViewAverageLifespan = view.findViewById(R.id.species_detail_averageLifespan)
        textViewAverageLifespanValue = view.findViewById(R.id.species_detail_averageLifespan_value)
        textViewPopulation = view.findViewById(R.id.species_detail_language)
        textViewPopulationValue = view.findViewById(R.id.species_detail_language_value)

        loader = view.findViewById(R.id.species_detail_loader)
        textViewError = view.findViewById(R.id.species_detail_error)

        val uid = arguments?.getString("uid") ?: ""

        viewModel.callApi(uid)
        viewModel.species.observe(viewLifecycleOwner, Observer { speciesDetailModel ->
            //loader.isVisible = filmDetailModel is FilmDetailLoader
            //textViewError.isVisible = filmDetailModel is FilmDetailError

            when(speciesDetailModel) {
                is SpeciesDetailSuccess -> {
                    textViewName.isVisible = true
                    textViewNameValue.isVisible = true
                    textViewClassification.isVisible = true
                    textViewClassificationValue.isVisible = true
                    textViewDesignation.isVisible = true
                    textViewDesignationValue.isVisible = true
                    textViewAverageHeight.isVisible = true
                    textViewAverageHeightValue.isVisible = true
                    textViewAverageLifespan.isVisible = true
                    textViewAverageLifespanValue.isVisible = true
                    textViewPopulation.isVisible = true
                    textViewPopulationValue.isVisible = true

                    textViewNameValue.text = speciesDetailModel.species.properties.name
                    textViewClassificationValue.text = speciesDetailModel.species.properties.classification
                    textViewDesignationValue.text = speciesDetailModel.species.properties.designation
                    textViewAverageHeightValue.text = speciesDetailModel.species.properties.average_height
                    textViewAverageLifespanValue.text = speciesDetailModel.species.properties.average_lifespan
                    textViewPopulationValue.text = speciesDetailModel.species.properties.language

                    loader.isVisible = false
                    textViewError.isVisible = false
                }

                SpeciesDetailError -> {
                    textViewName.isVisible = false
                    textViewNameValue.isVisible = false
                    textViewClassification.isVisible = false
                    textViewClassificationValue.isVisible = false
                    textViewDesignation.isVisible = false
                    textViewDesignationValue.isVisible = false
                    textViewAverageHeight.isVisible = false
                    textViewAverageHeightValue.isVisible = false
                    textViewAverageLifespan.isVisible = false
                    textViewAverageLifespanValue.isVisible = false
                    textViewPopulation.isVisible = false
                    textViewPopulationValue.isVisible = false

                    loader.isVisible = false
                    textViewError.isVisible = true
                }

                SpeciesDetailLoader -> {
                    textViewName.isVisible = false
                    textViewNameValue.isVisible = false
                    textViewClassification.isVisible = false
                    textViewClassificationValue.isVisible = false
                    textViewDesignation.isVisible = false
                    textViewDesignationValue.isVisible = false
                    textViewAverageHeight.isVisible = false
                    textViewAverageHeightValue.isVisible = false
                    textViewAverageLifespan.isVisible = false
                    textViewAverageLifespanValue.isVisible = false
                    textViewPopulation.isVisible = false
                    textViewPopulationValue.isVisible = false

                    loader.isVisible = true
                    textViewError.isVisible = false
                }
            }
        })

        view.findViewById<Button>(R.id.button_return).setOnClickListener {
            findNavController().navigate(R.id.action_SpeciesDetailFragment_to_SpeciesListFragment)
        }
    }
}