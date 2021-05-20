package com.example.project.presentation.detail

sealed class StarshipDetailModel

data class StarshipDetailSuccess(
        val starship : Starship
) : StarshipDetailModel()

object StarshipDetailError : StarshipDetailModel()

object StarshipDetailLoader : StarshipDetailModel()