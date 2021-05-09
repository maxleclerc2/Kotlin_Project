package com.example.project.presentation.detail.properties

data class VehicleProperties(
    val model: String,
    val vehicle_class: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val max_atmosphering_speed: String,
    val cargo_capacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val created: String,
    val edited: String,
    val name: String,
    val url: String
)