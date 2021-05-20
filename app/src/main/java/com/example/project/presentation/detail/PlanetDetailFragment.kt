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

class PlanetDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewNameValue: TextView
    private lateinit var textViewDiameter: TextView
    private lateinit var textViewDiameterValue: TextView
    private lateinit var textViewRotationPeriod: TextView
    private lateinit var textViewRotationPeriodValue: TextView
    private lateinit var textViewOrbitalPeriod: TextView
    private lateinit var textViewOrbitalPeriodValue: TextView
    private lateinit var textViewGravity: TextView
    private lateinit var textViewGravityValue: TextView
    private lateinit var textViewPopulation: TextView
    private lateinit var textViewPopulationValue: TextView
    private lateinit var textViewClimate: TextView
    private lateinit var textViewClimateValue: TextView
    private lateinit var textViewTerrain: TextView
    private lateinit var textViewTerrainValue: TextView

    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private val viewModel: PlanetDetailViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planet_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.planet_detail_name)
        textViewNameValue = view.findViewById(R.id.planet_detail_name_value)
        textViewDiameter = view.findViewById(R.id.planet_detail_diameter)
        textViewDiameterValue = view.findViewById(R.id.planet_detail_diameter_value)
        textViewRotationPeriod = view.findViewById(R.id.planet_detail_rotationPeriod)
        textViewRotationPeriodValue = view.findViewById(R.id.planet_detail_rotationPeriod_value)
        textViewOrbitalPeriod = view.findViewById(R.id.planet_detail_orbitalPeriod)
        textViewOrbitalPeriodValue = view.findViewById(R.id.planet_detail_orbitalPeriod_value)
        textViewGravity = view.findViewById(R.id.planet_detail_gravity)
        textViewGravityValue = view.findViewById(R.id.planet_detail_gravity_value)
        textViewPopulation = view.findViewById(R.id.planet_detail_population)
        textViewPopulationValue = view.findViewById(R.id.planet_detail_population_value)
        textViewClimate = view.findViewById(R.id.planet_detail_climate)
        textViewClimateValue = view.findViewById(R.id.planet_detail_climate_value)
        textViewTerrain = view.findViewById(R.id.planet_detail_terrain)
        textViewTerrainValue = view.findViewById(R.id.planet_detail_terrain_value)

        loader = view.findViewById(R.id.planet_detail_loader)
        textViewError = view.findViewById(R.id.planet_detail_error)

        val uid = arguments?.getString("uid") ?: ""

        viewModel.callApi(uid)
        viewModel.planet.observe(viewLifecycleOwner, Observer { planetDetailModel ->

            when(planetDetailModel) {
                is PlanetDetailSuccess -> {
                    textViewName.isVisible = true
                    textViewNameValue.isVisible = true
                    textViewDiameter.isVisible = true
                    textViewDiameterValue.isVisible = true
                    textViewRotationPeriod.isVisible = true
                    textViewRotationPeriodValue.isVisible = true
                    textViewOrbitalPeriod.isVisible = true
                    textViewOrbitalPeriodValue.isVisible = true
                    textViewGravity.isVisible = true
                    textViewGravityValue.isVisible = true
                    textViewPopulation.isVisible = true
                    textViewPopulationValue.isVisible = true
                    textViewClimate.isVisible = true
                    textViewClimateValue.isVisible = true
                    textViewTerrain.isVisible = true
                    textViewTerrainValue.isVisible = true

                    textViewNameValue.text = planetDetailModel.planet.properties.name
                    textViewDiameterValue.text = planetDetailModel.planet.properties.diameter
                    textViewRotationPeriodValue.text = planetDetailModel.planet.properties.rotation_period
                    textViewOrbitalPeriodValue.text = planetDetailModel.planet.properties.orbital_period
                    textViewGravityValue.text = planetDetailModel.planet.properties.gravity
                    textViewPopulationValue.text = planetDetailModel.planet.properties.population
                    textViewClimateValue.text = planetDetailModel.planet.properties.climate
                    textViewTerrainValue.text = planetDetailModel.planet.properties.terrain

                    loader.isVisible = false
                    textViewError.isVisible = false
                }

                PlanetDetailError -> {
                    textViewName.isVisible = false
                    textViewNameValue.isVisible = false
                    textViewDiameter.isVisible = false
                    textViewDiameterValue.isVisible = false
                    textViewRotationPeriod.isVisible = false
                    textViewRotationPeriodValue.isVisible = false
                    textViewOrbitalPeriod.isVisible = false
                    textViewOrbitalPeriodValue.isVisible = false
                    textViewGravity.isVisible = false
                    textViewGravityValue.isVisible = false
                    textViewPopulation.isVisible = false
                    textViewPopulationValue.isVisible = false
                    textViewClimate.isVisible = false
                    textViewClimateValue.isVisible = false
                    textViewTerrain.isVisible = false
                    textViewTerrainValue.isVisible = false

                    loader.isVisible = false
                    textViewError.isVisible = true
                }

                PlanetDetailLoader -> {
                    textViewName.isVisible = false
                    textViewNameValue.isVisible = false
                    textViewDiameter.isVisible = false
                    textViewDiameterValue.isVisible = false
                    textViewRotationPeriod.isVisible = false
                    textViewRotationPeriodValue.isVisible = false
                    textViewOrbitalPeriod.isVisible = false
                    textViewOrbitalPeriodValue.isVisible = false
                    textViewGravity.isVisible = false
                    textViewGravityValue.isVisible = false
                    textViewPopulation.isVisible = false
                    textViewPopulationValue.isVisible = false
                    textViewClimate.isVisible = false
                    textViewClimateValue.isVisible = false
                    textViewTerrain.isVisible = false
                    textViewTerrainValue.isVisible = false

                    loader.isVisible = true
                    textViewError.isVisible = false
                }
            }
        })

        view.findViewById<Button>(R.id.button_return).setOnClickListener {
            findNavController().navigate(R.id.action_PlanetDetailFragment_to_PlanetListFragment)
        }
    }
}