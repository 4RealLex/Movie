package com.example.movieapp2

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.movieapp2.ui.MoviesScreen
import com.example.movieapp2.ui.TvShowsScreen
import com.example.movieapp2.ui.SearchScreen
import com.example.movieapp2.ui.components.MovieDetailsScreen
import com.example.movieapp2.viewmodel.MovieViewModel
import androidx.navigation.navArgument


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp() {
    var selectedTab by remember { mutableStateOf(0) }
    val navController = rememberNavController()
    val movieViewModel: MovieViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies & TV Shows") },
                actions = {
                    IconButton(onClick = { navController.navigate("search") }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TabRow(selectedTabIndex = selectedTab) {
                        Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                            Text(text = "Movies", modifier = Modifier.padding(16.dp))
                        }
                        Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                            Text(text = "TV Shows", modifier = Modifier.padding(16.dp))
                        }
                    }

                    when (selectedTab) {
                        0 -> MoviesScreen(navController, movieViewModel)
                        1 -> TvShowsScreen(navController, movieViewModel)
                    }
                }
            }
            composable(
                route = "details/{movieId}/{mediaType}",
                arguments = listOf(
                    navArgument("movieId") { type = NavType.IntType },
                    navArgument("mediaType") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId")
                val mediaType = backStackEntry.arguments?.getString("mediaType") ?: "movie"

                val movie = when (mediaType) {
                    "movie" -> movieViewModel.getMovieById(movieId)
                    "tv" -> movieViewModel.getTvShowById(movieId)
                    else -> null
                }

                if (movie != null) {
                    MovieDetailsScreen(movie = movie, onBack = { navController.popBackStack() })
                } else {
                    Text("Movie not found")
                }
            }
            composable("search") {
                SearchScreen(navController, movieViewModel)
            }
        }
    }
}
