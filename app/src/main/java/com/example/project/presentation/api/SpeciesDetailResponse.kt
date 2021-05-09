package com.example.project.presentation.api

import com.example.project.presentation.detail.Species

data class SpeciesDetailResponse(
    val message: String,
    val result: Species
)