package com.example.galuianreceitas.data

import com.example.galuianreceitas.api.ApiClient
import com.example.galuianreceitas.api.ReceitaAPI
import com.example.galuianreceitas.data.Receita

class ReceitaRepository(private val receitaDao: ReceitaDao) {
    private val api: ReceitaAPI = ApiClient.receitaApi

    // Listar receitas do banco de dados
    suspend fun listarReceitas(): List<Receita> {
        return receitaDao.listarTodasReceitas()
    }

    // Inserir uma nova receita no banco de dados
    suspend fun inserirReceita(receita: Receita) {
        receitaDao.inserirReceita(receita)
    }

    // Atualizar uma receita existente no banco de dados
    suspend fun atualizarReceita(receita: Receita) {
        receitaDao.atualizarReceita(receita)
    }

    // Excluir uma receita do banco de dados
    suspend fun excluirReceita(receita: Receita) {
        receitaDao.excluirReceita(receita)
    }

    // Buscar receitas da API por ingredientes
    suspend fun buscarReceitasPorIngredientes(ingredientes: String): List<Receita>? {
        val response = api.buscarReceitas(ingredientes)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
