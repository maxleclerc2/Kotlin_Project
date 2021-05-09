package com.example.project.presentation.detail

import com.example.project.presentation.detail.properties.PeopleProperties

data class People(
    val properties: PeopleProperties,
    val description: String,
    val _id: String,
    val uid: String,
    val __v: Int
)