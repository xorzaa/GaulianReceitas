package com.example.galuianreceitas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.galuianreceitas.data.Receita
import com.example.galuianreceitas.data.ReceitaRepository
import com.example.galuianreceitas.data.DatabaseBuilder
import com.example.galuianreceitas.viewmodel.ReceitaViewModel
import com.example.galuianreceitas.viewmodel.ReceitaViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var receitaViewModel: ReceitaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar ReceitaDao e Repository
        val receitaDao = DatabaseBuilder.getDatabase(applicationContext).receitaDao()
        val repository = ReceitaRepository(receitaDao)

        // Criar a fábrica para o ViewModel
        val factory = ReceitaViewModelFactory(repository)

        // Criar o ViewModel com a fábrica
        receitaViewModel = ViewModelProvider(this, factory).get(ReceitaViewModel::class.java)

        // Exemplo: Observando a lista de receitas
        receitaViewModel.receitas.observe(this, Observer { receitas ->
            // Aqui você pode atualizar sua interface com a lista de receitas
            Toast.makeText(this, "Receitas carregadas: ${receitas.size}", Toast.LENGTH_SHORT).show()
        })

        // Carregar receitas do banco de dados
        receitaViewModel.carregarReceitas()

        // Exemplo: Buscar receitas por ingredientes
        val ingredientes = "tomato, cheese"
        receitaViewModel.buscarReceitasPorIngredientes(ingredientes)
    }
}