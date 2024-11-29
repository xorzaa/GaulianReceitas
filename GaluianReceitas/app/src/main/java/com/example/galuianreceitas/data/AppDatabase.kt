package com.example.galuianreceitas.data

import com.example.galuianreceitas.data.ReceitaDao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.galuianreceitas.data.Receita

@Database(entities = [Receita::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun receitaDao(): ReceitaDao
}

