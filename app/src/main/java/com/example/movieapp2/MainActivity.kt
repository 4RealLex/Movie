package com.example.movieapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp() {
    val movieList = remember { mutableStateListOf<Movie>() }

    // Replace with your method of retrieving the API key securely
    val apiKey = "72b79fffba075dc15f87acf6e58edb7c"

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitInstance.api.getPopularMovies(apiKey = apiKey)
            movieList.addAll(response.results)
        } catch (e: Exception) {
            // Handle the error appropriately (e.g., show a message to the user)
            e.printStackTrace()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Popular Movies") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movieList) { movie ->
                MovieCard(movie)
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val sampleMovie = Movie(
        id = 0,
        title = "Sample Movie",
        overview = "This is a sample movie overview.",
        poster_path = "/sample.jpg"
    )
    MovieCard(movie = sampleMovie)
}
