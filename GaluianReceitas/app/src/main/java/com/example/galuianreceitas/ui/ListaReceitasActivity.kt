package com.example.galuianreceitas.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galuianreceitas.R
import com.example.galuianreceitas.data.DatabaseBuilder
import com.example.galuianreceitas.data.ReceitaRepository
import com.example.galuianreceitas.databinding.ActivityListaReceitasBinding
import com.example.galuianreceitas.viewmodel.ReceitaViewModel
import com.example.galuianreceitas.viewmodel.ReceitaViewModelFactory
import com.example.galuianreceitas.ui.NovaReceitaActivity
import com.example.galuianreceitas.ui.DetalhesReceitaActivity

class ListaReceitasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaReceitasBinding // Declarando ViewBinding

    private val viewModel: ReceitaViewModel by lazy {
        val receitaDao = DatabaseBuilder.getDatabase(this).receitaDao()
        val repository = ReceitaRepository(receitaDao)
        ReceitaViewModelFactory(repository).create(ReceitaViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializando ViewBinding
        binding = ActivityListaReceitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView com ViewBinding
        binding.recyclerViewReceitas.layoutManager = LinearLayoutManager(this)

        viewModel.receitas.observe(this) { receitas ->
            binding.recyclerViewReceitas.adapter = ReceitaAdapter(receitas) { receita ->
                // Abrir tela de detalhes da receita
                val intent = Intent(this, DetalhesReceitaActivity::class.java)
                intent.putExtra("receitaId", receita.id)
                startActivity(intent)
            }
        }

        // Carregar receitas
        viewModel.carregarReceitas()

        // Abrir tela de nova receita
        binding.btnNovaReceita.setOnClickListener {
            val intent = Intent(this, NovaReceitaActivity::class.java)
            startActivity(intent)
        }
    }
}