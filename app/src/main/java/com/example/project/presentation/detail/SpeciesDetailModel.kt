package com.example.project.presentation.detail

sealed class SpeciesDetailModel

data class SpeciesDetailSuccess(
        val species : Species
) : SpeciesDetailModel()

object SpeciesDetailError : SpeciesDetailModel()

object SpeciesDetailLoader : SpeciesDetailModel()