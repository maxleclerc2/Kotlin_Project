package com.example.project.presentation.api

import com.example.project.presentation.detail.Film

data class FilmDetailResponse(
    val message: String,
    val result: Film
)