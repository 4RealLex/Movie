package com.example.movieapp2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp2.ui.components.MovieCard
import com.example.movieapp2.viewmodel.MovieViewModel
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, movieViewModel: MovieViewModel) {
    var query by remember { mutableStateOf("") }
    val searchResults by movieViewModel.searchResults.collectAsState()
    val isLoading by movieViewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search for movies or TV shows") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        movieViewModel.searchMovies(query)
                    }
                ) // Keeping this for basic keyboard action
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (searchResults.isNotEmpty()) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(searchResults) { movie ->
                            MovieCard(movie = movie, navController = navController)
                        }
                    }
                } else {
                    if (query.isNotEmpty()) {
                        Text("No results found for \"$query\"")
                    } else {
                        Text("Enter a query to search.")
                    }
                }
            }
        }
    }
}
