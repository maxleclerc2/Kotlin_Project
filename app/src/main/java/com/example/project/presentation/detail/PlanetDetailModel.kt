package com.example.project.presentation.detail

sealed class PlanetDetailModel

data class PlanetDetailSuccess(
        val planet : Planet
) : PlanetDetailModel()

object PlanetDetailError : PlanetDetailModel()

object PlanetDetailLoader : PlanetDetailModel()