package com.example.project.presentation.detail

import com.example.project.presentation.detail.properties.PlanetProperties

data class Planet(
    val properties: PlanetProperties,
    val description: String,
    val _id: String,
    val uid: String,
    val __v: Int
)