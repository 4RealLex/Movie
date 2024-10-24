package com.example.movieapp2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.movieapp2.BuildConfig
import com.example.movieapp2.RetrofitInstance
import com.example.movieapp2.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.mutableStateListOf

class MovieViewModel : ViewModel() {
    val popularMovies = mutableStateListOf<Movie>()
    val topRatedMovies = mutableStateListOf<Movie>()
    val popularTvShows = mutableStateListOf<Movie>()
    val topRatedTvShows = mutableStateListOf<Movie>()

    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchMovies()
        fetchTvShows()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val apiKey = BuildConfig.TMDB_API_KEY
                val popularResponse = RetrofitInstance.api.getPopularMovies(apiKey)
                popularMovies.addAll(popularResponse.results)

                val topRatedResponse = RetrofitInstance.api.getTopRatedMovies(apiKey)
                topRatedMovies.addAll(topRatedResponse.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchTvShows() {
        viewModelScope.launch {
            try {
                val apiKey = BuildConfig.TMDB_API_KEY
                val popularResponse = RetrofitInstance.api.getPopularTvShows(apiKey)
                popularTvShows.addAll(popularResponse.results)

                val topRatedResponse = RetrofitInstance.api.getTopRatedTvShows(apiKey)
                topRatedTvShows.addAll(topRatedResponse.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val apiKey = BuildConfig.TMDB_API_KEY
                val movieResponse = RetrofitInstance.api.searchMovies(apiKey, query)
                val tvResponse = RetrofitInstance.api.searchTvShows(apiKey, query)
                _searchResults.value = (movieResponse.results + tvResponse.results).distinctBy { it.id }
            } catch (e: Exception) {
                e.printStackTrace()
                _searchResults.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getMovieById(id: Int?): Movie? {
        return popularMovies.find { it.id == id }
            ?: topRatedMovies.find { it.id == id }
            ?: popularTvShows.find { it.id == id }
            ?: topRatedTvShows.find { it.id == id }
            ?: _searchResults.value.find { it.id == id }
    }
    fun getTvShowById(id: Int?): Movie? {
        return popularTvShows.find { it.id == id }
            ?: topRatedTvShows.find { it.id == id }
            ?: _searchResults.value.find { it.id == id }
    }

}
