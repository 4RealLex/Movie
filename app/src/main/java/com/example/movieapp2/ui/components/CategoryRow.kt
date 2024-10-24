package com.example.movieapp2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp2.model.Movie

@Composable
fun CategoryRow(
    categoryTitle: String,
    movies: List<Movie>,
    navController: NavController
) {
    Column {
        Text(
            text = categoryTitle,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(movies) { movie ->
                MovieCard(movie = movie, navController = navController)
            }
        }
    }
}
