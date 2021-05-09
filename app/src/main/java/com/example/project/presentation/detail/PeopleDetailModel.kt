package com.example.project.presentation.detail

sealed class PeopleDetailModel

data class PeopleDetailSuccess(
        val people : People
) : PeopleDetailModel()

object PeopleDetailError : PeopleDetailModel()

object PeopleDetailLoader : PeopleDetailModel()