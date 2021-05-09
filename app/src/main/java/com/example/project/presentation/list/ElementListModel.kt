package com.example.project.presentation.list

sealed class ElementListModel

data class ElementListSuccess(
        val elementList : List<Element>
) : ElementListModel()

object ElementListError : ElementListModel()

object ElementListLoader : ElementListModel()