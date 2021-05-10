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

class PeopleDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewNameValue: TextView
    private lateinit var textViewHeight: TextView
    private lateinit var textViewHeightValue: TextView
    private lateinit var textViewMass: TextView
    private lateinit var textViewMassValue: TextView
    private lateinit var textViewHairColor: TextView
    private lateinit var textViewHairColorValue: TextView
    private lateinit var textViewSkinColor: TextView
    private lateinit var textViewSkinColorValue: TextView
    private lateinit var textViewEyeColor: TextView
    private lateinit var textViewEyeColorValue: TextView
    private lateinit var textViewBirthYear: TextView
    private lateinit var textViewBirthYearValue: TextView
    private lateinit var textViewGender: TextView
    private lateinit var textViewGenderValue: TextView

    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private val viewModel: PeopleDetailViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.people_detail_name)
        textViewNameValue = view.findViewById(R.id.people_detail_name_value)
        textViewHeight = view.findViewById(R.id.people_detail_height)
        textViewHeightValue = view.findViewById(R.id.people_detail_height_value)
        textViewMass = view.findViewById(R.id.people_detail_mass)
        textViewMassValue = view.findViewById(R.id.people_detail_mass_value)
        textViewHairColor = view.findViewById(R.id.people_detail_hairColor)
        textViewHairColorValue = view.findViewById(R.id.people_detail_hairColor_value)
        textViewSkinColor = view.findViewById(R.id.people_detail_skinColor)
        textViewSkinColorValue = view.findViewById(R.id.people_detail_skinColor_value)
        textViewEyeColor = view.findViewById(R.id.people_detail_eyeColor)
        textViewEyeColorValue = view.findViewById(R.id.people_detail_eyeColor_Value)
        textViewBirthYear = view.findViewById(R.id.people_detail_birthYear)
        textViewBirthYearValue = view.findViewById(R.id.people_detail_birthYear_value)
        textViewGender = view.findViewById(R.id.people_detail_gender)
        textViewGenderValue = view.findViewById(R.id.people_detail_gender_value)

        loader = view.findViewById(R.id.people_detail_loader)
        textViewError = view.findViewById(R.id.people_detail_error)

        val uid = arguments?.getString("uid") ?: ""

        viewModel.callApi(uid)
        viewModel.people.observe(viewLifecycleOwner, Observer { peopleDetailModel ->
            //loader.isVisible = filmDetailModel is FilmDetailLoader
            //textViewError.isVisible = filmDetailModel is FilmDetailError

            when(peopleDetailModel) {
                is PeopleDetailSuccess -> {
                    textViewName.isVisible = true
                    textViewNameValue.isVisible = true
                    textViewHeight.isVisible = true
                    textViewHeightValue.isVisible = true
                    textViewMass.isVisible = true
                    textViewMassValue.isVisible = true
                    textViewHairColor.isVisible = true
                    textViewHairColorValue.isVisible = true
                    textViewSkinColor.isVisible = true
                    textViewSkinColorValue.isVisible = true
                    textViewEyeColor.isVisible = true
                    textViewEyeColorValue.isVisible = true
                    textViewBirthYear.isVisible = true
                    textViewBirthYearValue.isVisible = true
                    textViewGender.isVisible = true
                    textViewGenderValue.isVisible = true

                    textViewNameValue.text = peopleDetailModel.people.properties.name
                    textViewHeightValue.text = peopleDetailModel.people.properties.height
                    textViewMassValue.text = peopleDetailModel.people.properties.mass
                    textViewHairColorValue.text = peopleDetailModel.people.properties.hair_color
                    textViewSkinColorValue.text = peopleDetailModel.people.properties.skin_color
                    textViewEyeColorValue.text = peopleDetailModel.people.properties.eye_color
                    textViewBirthYearValue.text = peopleDetailModel.people.properties.birth_year
                    textViewGenderValue.text = peopleDetailModel.people.properties.gender

                    loader.isVisible = false
                    textViewError.isVisible = false
                }

                PeopleDetailError -> {
                    textViewName.isVisible = false
                    textViewNameValue.isVisible = false
                    textViewHeight.isVisible = false
                    textViewHeightValue.isVisible = false
                    textViewMass.isVisible = false
                    textViewMassValue.isVisible = false
                    textViewHairColor.isVisible = false
                    textViewHairColorValue.isVisible = false
                    textViewSkinColor.isVisible = false
                    textViewSkinColorValue.isVisible = false
                    textViewEyeColor.isVisible = false
                    textViewEyeColorValue.isVisible = false
                    textViewBirthYear.isVisible = false
                    textViewBirthYearValue.isVisible = false
                    textViewGender.isVisible = false
                    textViewGenderValue.isVisible = false

                    loader.isVisible = false
                    textViewError.isVisible = true
                }

                PeopleDetailLoader -> {
                    textViewName.isVisible = false
                    textViewNameValue.isVisible = false
                    textViewHeight.isVisible = false
                    textViewHeightValue.isVisible = false
                    textViewMass.isVisible = false
                    textViewMassValue.isVisible = false
                    textViewHairColor.isVisible = false
                    textViewHairColorValue.isVisible = false
                    textViewSkinColor.isVisible = false
                    textViewSkinColorValue.isVisible = false
                    textViewEyeColor.isVisible = false
                    textViewEyeColorValue.isVisible = false
                    textViewBirthYear.isVisible = false
                    textViewBirthYearValue.isVisible = false
                    textViewGender.isVisible = false
                    textViewGenderValue.isVisible = false

                    loader.isVisible = true
                    textViewError.isVisible = false
                }
            }
        })

        view.findViewById<Button>(R.id.button_return).setOnClickListener {
            findNavController().navigate(R.id.action_PeopleDetailFragment_to_PeopleListFragment)
        }
    }
}