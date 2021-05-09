package com.example.project.presentation.api

import com.example.project.presentation.detail.Starship

data class StarshipDetailResponse(
    val message: String,
    val result: Starship
)
