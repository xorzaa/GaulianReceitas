package com.seuprojeto.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seuprojeto.R
import com.seuprojeto.data.DatabaseBuilder
import com.seuprojeto.data.Receita
import com.seuprojeto.viewmodel.ReceitaViewModel
import com.seuprojeto.viewmodel.ReceitaViewModelFactory
import kotlinx.android.synthetic.main.activity_nova_receita.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovaReceitaActivity : AppCompatActivity() {

    private val viewModel: ReceitaViewModel by lazy {
        ReceitaViewModelFactory(DatabaseBuilder.getDatabase(this).receitaDao())
            .create(ReceitaViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_receita)

        btnSalvarReceita.setOnClickListener {
            salvarReceita()
        }
    }

    private fun salvarReceita() {
        // Obter os dados do formulário
        val nome = etNomeReceita.text.toString()
        val ingredientes = etIngredientes.text.toString()
        val modoPreparo = etModoPreparo.text.toString()
        val tempo = etTempo.text.toString().toIntOrNull()

        // Validar os dados
        if (nome.isBlank() || ingredientes.isBlank() || modoPreparo.isBlank() || tempo == null) {
            Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            return
        }

        // Criar objeto Receita
        val receita = Receita(
            id = 0, // Será gerado automaticamente pelo Room
            nome = nome,
            ingredientes = ingredientes,
            modoPreparo = modoPreparo,
            tempo = tempo
        )

        // Salvar no banco de dados usando a ViewModel
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.adicionarReceita(receita)
            runOnUiThread {
                Toast.makeText(this@NovaReceitaActivity, "Receita salva com sucesso!", Toast.LENGTH_SHORT).show()
                finish() // Voltar para a tela anterior
            }
        }
    }
}