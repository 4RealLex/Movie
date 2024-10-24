package com.example.movieapp2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp2.model.Movie
import kotlinx.coroutines.delay
import androidx.compose.ui.unit.dp

@Composable
fun HeroSection(movies: List<Movie>) {
    if (movies.isNotEmpty()) {
        var currentIndex by remember { mutableStateOf(0) }

        LaunchedEffect(Unit) {
            while (true) {
                delay(5000) // Change image every 5 seconds
                currentIndex = (currentIndex + 1) % movies.size
            }
        }

        val movie = movies[currentIndex]

        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w780/${movie.backdrop_path}"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
    }
}
