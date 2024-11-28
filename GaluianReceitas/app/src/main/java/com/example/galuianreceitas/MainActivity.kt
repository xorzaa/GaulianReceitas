package com.example.galuianreceitas

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.galuianreceitas.data.DatabaseBuilder
import com.example.galuianreceitas.data.ReceitaRepository
import com.example.galuianreceitas.viewmodel.ReceitaViewModel
import com.example.galuianreceitas.viewmodel.ReceitaViewModelFactory

class MainActivity : AppCompatActivity() {

    // Inicializar o ViewModel com o ViewModelFactory
    private val viewModel: ReceitaViewModel by viewModels {
        ReceitaViewModelFactory(ReceitaRepository(DatabaseBuilder.getDatabase(this).receitaDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observar as receitas
        viewModel.receitas.observe(this, Observer { receitas ->
            // Atualize a interface do usuário com as receitas
            receitas.forEach { receita ->
                println("Receita: ${receita.nome}")
            }
        })

        // Carregar as receitas ao iniciar o app
        viewModel.carregarReceitas()
    }
}
