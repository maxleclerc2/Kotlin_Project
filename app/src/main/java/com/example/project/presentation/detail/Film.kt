package com.example.project.presentation.detail

import com.example.project.presentation.detail.properties.FilmProperties

data class Film(
    val properties: FilmProperties,
    val description: String,
    val _id: String,
    val uid: String,
    val __v: Int
)