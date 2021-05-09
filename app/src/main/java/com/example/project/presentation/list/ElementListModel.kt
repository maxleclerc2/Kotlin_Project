package com.example.project.presentation.list

sealed class ElementListModel

data class ElementListSuccess(
        val elementList : List<Element>,
        val previous: String?,
        val next: String?
) : ElementListModel()

object ElementListError : ElementListModel()

object ElementListLoader : ElementListModel()