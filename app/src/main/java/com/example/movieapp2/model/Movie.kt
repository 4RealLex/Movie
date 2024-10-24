package com.example.movieapp2.model

data class Movie(
    val id: Int,
    val title: String?,       // For movies
    val name: String?,        // For TV shows
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?
)

data class MovieResponse(
    val results: List<Movie>
)
