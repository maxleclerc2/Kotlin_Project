package com.example.project.presentation.list

import com.example.project.presentation.detail.Film

sealed class FilmListModel

data class FilmListSuccess(
        val filmList : List<Film>
) : FilmListModel()

object FilmListError : FilmListModel()

object FilmListLoader : FilmListModel()