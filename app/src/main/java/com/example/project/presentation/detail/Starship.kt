package com.example.project.presentation.detail

import com.example.project.presentation.detail.properties.StarshipProperties

data class Starship(
    val properties: StarshipProperties,
    val description: String,
    val _id: String,
    val uid: String,
    val __v: Int
)