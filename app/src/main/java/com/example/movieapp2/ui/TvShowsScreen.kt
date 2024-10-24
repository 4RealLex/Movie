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
fun TvShowsScreen(navController: NavController, movieViewModel: MovieViewModel) {
    val popularTvShows = movieViewModel.popularTvShows
    val topRatedTvShows = movieViewModel.topRatedTvShows

    LazyColumn {
        item {
            HeroSection(movies = topRatedTvShows.take(5))
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            CategoryRow(
                categoryTitle = "Popular TV Shows",
                movies = popularTvShows,
                navController = navController
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            CategoryRow(
                categoryTitle = "Top Rated TV Shows",
                movies = topRatedTvShows,
                navController = navController
            )
        }
    }
}

