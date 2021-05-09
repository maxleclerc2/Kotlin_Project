package com.example.project.presentation.detail.properties

data class StarshipProperties(
    val model: String,
    val starship_class: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val max_atmosphering_speed: String,
    val hyperdrive_rating: String,
    val MGLT: String,
    val cargo_capacity: String,
    val consumables: String,
    val pilots: List<String>,
    val created: String,
    val edited: String,
    val name: String,
    val url: String
)