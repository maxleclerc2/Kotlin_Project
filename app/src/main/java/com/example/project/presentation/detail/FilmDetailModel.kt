package com.example.project.presentation.detail

import com.example.project.presentation.list.FilmListModel

sealed class FilmDetailModel

data class FilmDetailSuccess(
        val film : Film
) : FilmDetailModel()

object FilmDetailError : FilmDetailModel()

object FilmDetailLoader : FilmDetailModel()