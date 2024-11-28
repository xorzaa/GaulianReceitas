package com.example.galuianreceitas.data

import androidx.room.*

@Dao
interface ReceitaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirReceita(receita: Receita)

    @Query("SELECT * FROM receitas")
    suspend fun listarReceitas(): List<Receita>

    @Update
    suspend fun atualizarReceita(receita: Receita)

    @Delete
    suspend fun excluirReceita(receita: Receita)
}

