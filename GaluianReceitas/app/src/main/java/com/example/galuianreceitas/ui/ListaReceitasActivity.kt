package com.seuprojeto.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.seuprojeto.R
import com.seuprojeto.data.DatabaseBuilder
import com.seuprojeto.viewmodel.ReceitaViewModel
import com.seuprojeto.viewmodel.ReceitaViewModelFactory
import kotlinx.android.synthetic.main.activity_lista_receitas.*

class ListaReceitasActivity : AppCompatActivity() {

    private val viewModel: ReceitaViewModel by lazy {
        ReceitaViewModelFactory(DatabaseBuilder.getDatabase(this).receitaDao())
            .create(ReceitaViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_receitas)

        // Configurar RecyclerView
        recyclerViewReceitas.layoutManager = LinearLayoutManager(this)
        viewModel.receitas.observe(this) { receitas ->
            recyclerViewReceitas.adapter = ReceitaAdapter(receitas) { receita ->
                // Abrir tela de detalhes da receita
                val intent = Intent(this, DetalhesReceitaActivity::class.java)
                intent.putExtra("receitaId", receita.id)
                startActivity(intent)
            }
        }

        // Carregar receitas
        viewModel.carregarReceitas()

        // Abrir tela de nova receita
        btnNovaReceita.setOnClickListener {
            val intent = Intent(this, NovaReceitaActivity::class.java)
            startActivity(intent)
        }
    }
}