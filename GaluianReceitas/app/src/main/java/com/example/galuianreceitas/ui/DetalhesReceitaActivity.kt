package com.example.galuianreceitas.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.galuianreceitas.data.DatabaseBuilder
import com.example.galuianreceitas.data.ReceitaRepository
import com.example.galuianreceitas.data.Receita // Importando a classe Receita corretamente
import com.example.galuianreceitas.viewmodel.ReceitaViewModel
import com.example.galuianreceitas.viewmodel.ReceitaViewModelFactory
import com.example.galuianreceitas.R

class DetalhesReceitaActivity : AppCompatActivity() {

    // Inicializando as variáveis para os componentes da interface
    private lateinit var tvNomeDetalhe: TextView
    private lateinit var tvIngredientesDetalhe: TextView
    private lateinit var tvModoPreparoDetalhe: TextView
    private lateinit var btnCompartilharReceita: Button

    // Inicializando o ViewModel com a fábrica
    private val viewModel: ReceitaViewModel by lazy {
        val receitaDao = DatabaseBuilder.getDatabase(this).receitaDao()
        val repository = ReceitaRepository(receitaDao)
        ReceitaViewModelFactory(repository).create(ReceitaViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhes_receita)

        // Inicializando as variáveis com findViewById
        tvNomeDetalhe = findViewById(R.id.tvNomeDetalhe)
        tvIngredientesDetalhe = findViewById(R.id.tvIngredientesDetalhe)
        tvModoPreparoDetalhe = findViewById(R.id.tvModoPreparoDetalhe)
        btnCompartilharReceita = findViewById(R.id.btnCompartilharReceita)

        // Obtendo o id da receita passada pela Intent
        val receitaId = intent.getIntExtra("receitaId", -1)

        // Observando as receitas e preenchendo os detalhes da receita selecionada
        viewModel.receitas.observe(this) { receitas ->
            val receita = receitas.find { it.id == receitaId.toLong() }  // Converte receitaId para Long
            receita?.let {
                // Acessando as propriedades da receita corretamente
                tvNomeDetalhe.text = it.nome
                tvIngredientesDetalhe.text = it.ingredientes
                tvModoPreparoDetalhe.text = it.modoPreparo

                // Configurando o botão de compartilhamento
                btnCompartilharReceita.setOnClickListener {
                    // Passando o objeto Receita 'it' corretamente para o método
                    compartilharReceita(receita)  // 'receita' já é o objeto correto
                }
            }
        }
    }

    private fun compartilharReceita(receita: Receita) {
        // Criando o texto para compartilhamento
        val textoCompartilhamento = """
            Receita: ${receita.nome}
            
            Ingredientes:
            ${receita.ingredientes}
            
            Modo de Preparo:
            ${receita.modoPreparo}
            
            Tempo de Execução: ${receita.tempo}
            Categoria: ${receita.categoria}
            
            Bom apetite!
        """.trimIndent()

        // Configurando o Intent de compartilhamento
        val intentCompartilhar = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, textoCompartilhamento)
        }

        // Abrindo o menu de compartilhamento
        startActivity(Intent.createChooser(intentCompartilhar, "Compartilhar Receita"))
    }
}
