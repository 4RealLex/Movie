package com.example.movieapp2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp2.model.Movie

@Composable
fun MovieCard(movie: Movie, navController: NavController) {
    val mediaType = if (movie.title != null) "movie" else "tv"
    Card(
        modifier = Modifier
            .width(150.dp)
            .clickable {
                navController.navigate("details/${movie.id}/$mediaType")
            }
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.title ?: movie.name ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp),
                maxLines = 1
            )
        }
    }
}
