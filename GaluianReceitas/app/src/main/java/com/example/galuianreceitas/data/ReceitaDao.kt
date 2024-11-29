package com.example.galuianreceitas.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceitaDao {

    // Método para listar todas as receitas
    @Query("SELECT * FROM receitas")
    fun listarReceitas(): Flow<List<Receita>>

    // Método para inserir uma nova receita
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirReceita(receita: Receita)

    // Método para atualizar uma receita existente
    @Update
    suspend fun atualizarReceita(receita: Receita)

    // Método para excluir uma receita
    @Delete
    suspend fun excluirReceita(receita: Receita)

    // Método auxiliar para inserir uma receita sem retornar nada
    @Insert
    fun insert(receita: Receita)
}
