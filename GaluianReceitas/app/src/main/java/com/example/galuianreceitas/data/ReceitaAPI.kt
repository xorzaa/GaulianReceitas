package com.example.galuianreceitas.data
import com.example.galuianreceitas.BuildConfig

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// ReceitaAPI.kt
interface ReceitaAPI {
    @GET("recipes/findByIngredients")
    suspend fun buscarReceitas(
        @Query("ingredients") ingredientes: String,
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_API_KEY,
        @Query("number") numeroReceitas: Int = 10
    ): Response<List<Receita>>
}