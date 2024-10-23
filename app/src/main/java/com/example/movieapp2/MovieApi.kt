package com.example.movieapp2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// TMDb API interface
interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String = "72b79fffba075dc15f87acf6e58edb7c"): MovieResponse
}

// Retrofit builder
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
