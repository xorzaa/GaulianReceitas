package com.example.galuianreceitas.data

import com.example.galuianreceitas.api.ApiClient
import kotlinx.coroutines.flow.Flow

class ReceitaRepository(private val receitaDao: ReceitaDao) {

    private val api = ApiClient.getApiService()

    suspend fun adicionarReceita(receita: Receita) {
        receitaDao.insert(receita)
    }

    suspend fun atualizarReceita(receita: Receita) {
        receitaDao.atualizarReceita(receita)
    }

    suspend fun excluirReceita(receita: Receita) {
        receitaDao.excluirReceita(receita)
    }

    suspend fun buscarReceitasPorIngredientes(ingredientes: String): List<Receita>? {
        val response = api.buscarReceitas(ingredientes)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    fun listarReceitas(): Flow<List<Receita>> {
        return receitaDao.listarReceitas()
    }
}