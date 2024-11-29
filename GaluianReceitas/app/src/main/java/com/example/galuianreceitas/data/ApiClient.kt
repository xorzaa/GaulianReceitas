package com.example.galuianreceitas.api

import com.example.galuianreceitas.data.ReceitaAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://api.spoonacular.com/" // URL base da API

    // Inicializa o Retrofit
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Método para acessar a API
    fun getApiService(): ReceitaAPI {
        return retrofit.create(ReceitaAPI::class.java)
    }
}
