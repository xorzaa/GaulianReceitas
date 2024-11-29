package com.example.galuianreceitas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receitas")
data class Receita(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val ingredientes: String,
    val modoPreparo: String,
    val tempo: Int,
    val categoria: String // Adicionado o campo 'categoria'
)
