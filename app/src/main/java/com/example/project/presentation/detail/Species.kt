package com.example.project.presentation.detail

import com.example.project.presentation.detail.properties.SpeciesProperties

data class Species(
    val properties: SpeciesProperties,
    val description: String,
    val _id: String,
    val uid: String,
    val __v: Int
)