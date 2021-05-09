package com.example.project.presentation.detail.properties

data class SpeciesProperties(
    val classification: String,
    val designation: String,
    val average_height: String,
    val average_lifespan: String,
    val hair_colors: String,
    val skin_colors: String,
    val eye_colors: String,
    val people: List<String>,
    val created: String,
    val edited: String,
    val name: String,
    val url: String
)