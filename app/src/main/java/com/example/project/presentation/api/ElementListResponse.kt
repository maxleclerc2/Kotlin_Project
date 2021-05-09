package com.example.project.presentation.api

import com.example.project.presentation.list.Element

data class ElementListResponse(
    val message: String,
    val total_records: Int,
    val total_pages: Int,
    val previous: String,
    val next: String,
    val results: List<Element>
)