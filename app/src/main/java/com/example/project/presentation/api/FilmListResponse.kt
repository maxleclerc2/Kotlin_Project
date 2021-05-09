package com.example.project.presentation.api

import com.example.project.presentation.detail.Film

data class FilmListResponse(
    val message: String,
    val result: List<Film>
)
