package com.example.project.presentation.api

import com.example.project.presentation.detail.Planet

data class PlanetDetailResponse(
    val message: String,
    val result: Planet
)