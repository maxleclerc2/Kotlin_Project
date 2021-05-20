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

class StarshipDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewNameValue: TextView
    private lateinit var textViewModel: TextView
    private lateinit var textViewModelValue: TextView
    private lateinit var textViewClass: TextView
    private lateinit var textViewClassValue: TextView
    private lateinit var textViewManufacturer: TextView
    private lateinit var textViewManufacturerValue: TextView
    private lateinit var textViewCost: TextView
    private lateinit var textViewCostValue: TextView
    private lateinit var textViewLength: TextView
    private lateinit var textViewLengthValue: TextView
    private lateinit var textViewCrew: TextView
    private lateinit var textViewCrewValue: TextView
    private lateinit var textViewPassengers: TextView
    private lateinit var textViewPassengersValue: TextView

    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private val viewModel: StarshipDetailViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_starship_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.starship_detail_name)
        textViewNameValue = view.findViewById(R.id.starship_detail_name_value)
        textViewModel = view.findViewById(R.id.starship_detail_model)
        textViewModelValue = view.findViewById(R.id.starship_detail_model_value)
        textViewClass = view.findViewById(R.id.starship_detail_class)
        textViewClassValue = view.findViewById(R.id.starship_detail_class_value)
        textViewManufacturer = view.findViewById(R.id.starship_detail_manufacturer)
        textViewManufacturerValue = view.findViewById(R.id.starship_detail_manufacturer_value)
        textViewCost = view.findViewById(R.id.starship_detail_cost)
        textViewCostValue = view.findViewById(R.id.starship_detail_cost_value)
        textViewLength = view.findViewById(R.id.starship_detail_length)
        textViewLengthValue = view.findViewById(R.id.starship_detail_length_value)
        textViewCrew = view.findViewById(R.id.starship_detail_crew)
        textViewCrewValue = view.findViewById(R.id.starship_detail_crew_value)
        textViewPassengers = view.findViewById(R.id.starship_detail_passengers)
        textViewPassengersValue = view.findViewById(R.id.starship_detail_passengers_value)

        loader = view.findViewById(R.id.starship_detail_loader)
        textViewError = view.findViewById(R.id.starship_detail_error)

        val uid = arguments?.getString("uid") ?: ""

        viewModel.callApi(uid)
        viewModel.starship.observe(viewLifecycleOwner, Observer { starshipDetailModel ->

            when(starshipDetailModel) {
                is StarshipDetailSuccess -> {
                    textViewName.isVisible = true
                    textViewNameValue.isVisible = true
                    textViewModel.isVisible = true
                    textViewModelValue.isVisible = true
                    textViewClass.isVisible = true
                    textViewClassValue.isVisible = true
                    textViewManufacturer.isVisible = true
                    textViewManufacturerValue.isVisible = true
                    textViewCost.isVisible = true
                    textViewCostValue.isVisible = true
                    textViewLength.isVisible = true
                    textViewLengthValue.isVisible = true
                    textViewCrew.isVisible = true
                    textViewCrewValue.isVisible = true
                    textViewPassengers.isVisible = true
                    textViewPassengersValue.isVisible = true

                    textViewNameValue.text = starshipDetailModel.starship.properties.name
                    textViewModelValue.text = starshipDetailModel.starship.properties.model
                    textViewClassValue.text = starshipDetailModel.starship.properties.starship_class
                    textViewManufacturerValue.text = starshipDetailModel.starship.properties.manufacturer
                    textViewCostValue.text = starshipDetailModel.starship.properties.cost_in_credits
                    textViewLengthValue.text = starshipDetailModel.starship.properties.length
                    textViewCrewValue.text = starshipDetailModel.starship.properties.crew
                    textViewPassengersValue.text = starshipDetailModel.starship.properties.passengers

                    loader.isVisible = false
                    textViewError.isVisible = false
                }

                StarshipDetailError -> {
                    textViewName.isVisible = false
                    textViewNameValue.isVisible = false
                    textViewModel.isVisible = false
                    textViewModelValue.isVisible = false
                    textViewClass.isVisible = false
                    textViewClassValue.isVisible = false
                    textViewManufacturer.isVisible = false
                    textViewManufacturerValue.isVisible = false
                    textViewCost.isVisible = false
                    textViewCostValue.isVisible = false
                    textViewLength.isVisible = false
                    textViewLengthValue.isVisible = false
                    textViewCrew.isVisible = false
                    textViewCrewValue.isVisible = false
                    textViewPassengers.isVisible = false
                    textViewPassengersValue.isVisible = false

                    loader.isVisible = false
                    textViewError.isVisible = true
                }

                StarshipDetailLoader -> {
                    textViewName.isVisible = false
                    textViewNameValue.isVisible = false
                    textViewModel.isVisible = false
                    textViewModelValue.isVisible = false
                    textViewClass.isVisible = false
                    textViewClassValue.isVisible = false
                    textViewManufacturer.isVisible = false
                    textViewManufacturerValue.isVisible = false
                    textViewCost.isVisible = false
                    textViewCostValue.isVisible = false
                    textViewLength.isVisible = false
                    textViewLengthValue.isVisible = false
                    textViewCrew.isVisible = false
                    textViewCrewValue.isVisible = false
                    textViewPassengers.isVisible = false
                    textViewPassengersValue.isVisible = false

                    loader.isVisible = true
                    textViewError.isVisible = false
                }
            }
        })

        view.findViewById<Button>(R.id.button_return).setOnClickListener {
            findNavController().navigate(R.id.action_StarshipDetailFragment_to_StarshipListFragment)
        }
    }
}