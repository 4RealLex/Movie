package com.example.movieapp2

import com.example.movieapp2.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String): MovieResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(@Query("api_key") apiKey: String): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResponse

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResponse
}
