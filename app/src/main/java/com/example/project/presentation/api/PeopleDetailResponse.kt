package com.example.project.presentation.api

import com.example.project.presentation.detail.People

data class PeopleDetailResponse(
    val message: String,
    val result: People
)