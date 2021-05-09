package com.example.project.presentation.detail

import com.example.project.presentation.detail.properties.VehicleProperties

data class Vehicle(
    val properties: VehicleProperties,
    val description: String,
    val _id: String,
    val uid: String,
    val __v: Int
)