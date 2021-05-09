package com.example.project.presentation.detail.properties

data class FilmProperties(
        val characters: List<String>,
        val planets: List<String>,
        val starships: List<String>,
        val vehicles: List<String>,
        val species: List<String>,
        val created: String,
        val edited: String,
        val producer: String,
        var title: String,
        val episode_id: Int,
        val director: String,
        val release_date: String,
        val opening_crawl: String,
        val url: String
)