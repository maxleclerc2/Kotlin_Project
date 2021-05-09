package com.example.project.presentation.detail

sealed class FilmDetailModel

data class FilmDetailSuccess(
        val film : Film
) : FilmDetailModel()

object FilmDetailError : FilmDetailModel()

object FilmDetailLoader : FilmDetailModel()