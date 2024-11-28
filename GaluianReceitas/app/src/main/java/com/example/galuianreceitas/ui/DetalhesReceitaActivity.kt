package com.seuprojeto.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seuprojeto.R
import com.seuprojeto.data.DatabaseBuilder
import com.seuprojeto.viewmodel.ReceitaViewModel
import com.seuprojeto.viewmodel.ReceitaViewModelFactory
import kotlinx.android.synthetic.main.detalhes_receita.*

class DetalhesReceitaActivity : AppCompatActivity() {

    private val viewModel: ReceitaViewModel by lazy {
        ReceitaViewModelFactory(DatabaseBuilder.getDatabase(this).receitaDao())
            .create(ReceitaViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhes_receita)

        val receitaId = intent.getIntExtra("receitaId", -1)

        // Observar receitas e preencher os detalhes da receita selecionada
        viewModel.receitas.observe(this) { receitas ->
            val receita = receitas.find { it.id == receitaId }
            receita?.let {
                tvNomeDetalhe.text = it.nome
                tvIngredientesDetalhe.text = it.ingredientes
                tvModoPreparoDetalhe.text = it.modoPreparo

                // Configurar botão de compartilhamento
                btnCompartilharReceita.setOnClickListener {
                    compartilharReceita(it.nome, it.ingredientes, it.modoPreparo)
                }
            }
        }
    }

    private fun compartilharReceita(nome: String, ingredientes: String, modoPreparo: String) {
        // Criar o texto para compartilhamento
        val textoCompartilhamento = """
            Receita: $nome
            
            Ingredientes:
            $ingredientes
            
            Modo de Preparo:
            $modoPreparo
            
            Bom apetite!
        """.trimIndent()

        // Configurar o Intent de compartilhamento
        val intentCompartilhar = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, textoCompartilhamento)
        }

        // Abrir o menu de compartilhamento
        startActivity(Intent.createChooser(intentCompartilhar, "Compartilhar Receita"))
    }
}