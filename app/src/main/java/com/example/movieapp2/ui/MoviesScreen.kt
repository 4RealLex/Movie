package com.example.movieapp2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp2.ui.components.CategoryRow
import com.example.movieapp2.ui.components.HeroSection
import com.example.movieapp2.viewmodel.MovieViewModel

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun MoviesScreen(navController: NavController, movieViewModel: MovieViewModel) {
    val popularMovies = movieViewModel.popularMovies
    val topRatedMovies = movieViewModel.topRatedMovies

    LazyColumn {
        item {
            HeroSection(movies = topRatedMovies.take(5))
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            CategoryRow(
                categoryTitle = "Popular Movies",
                movies = popularMovies,
                navController = navController
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            CategoryRow(
                categoryTitle = "Top Rated Movies",
                movies = topRatedMovies,
                navController = navController
            )
        }
    }
}

