package com.example.galuianreceitas.data


class ReceitaRepository(private val dao: ReceitaDao) {
    suspend fun inserirReceita(receita: Receita) = dao.inserirReceita(receita)
    suspend fun listarReceitas() = dao.listarReceitas()
    suspend fun atualizarReceita(receita: Receita) = dao.atualizarReceita(receita)
    suspend fun excluirReceita(receita: Receita) = dao.excluirReceita(receita)
}


