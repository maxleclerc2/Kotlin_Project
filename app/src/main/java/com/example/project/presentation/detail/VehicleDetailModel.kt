package com.example.project.presentation.detail

sealed class VehicleDetailModel

data class VehicleDetailSuccess(
        val vehicle : Vehicle
) : VehicleDetailModel()

object VehicleDetailError : VehicleDetailModel()

object VehicleDetailLoader : VehicleDetailModel()