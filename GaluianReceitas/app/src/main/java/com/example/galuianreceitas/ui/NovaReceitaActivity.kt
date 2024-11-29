package com.example.galuianreceitas.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.galuianreceitas.R
import com.example.galuianreceitas.data.DatabaseBuilder
import com.example.galuianreceitas.data.Receita
import com.example.galuianreceitas.data.ReceitaRepository
import com.example.galuianreceitas.viewmodel.ReceitaViewModel
import com.example.galuianreceitas.viewmodel.ReceitaViewModelFactory
import com.example.galuianreceitas.databinding.ActivityNovaReceitaBinding // Importa a classe gerada pelo ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovaReceitaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNovaReceitaBinding // Declare o ViewBinding

    private val viewModel: ReceitaViewModel by lazy {
        val receitaDao = DatabaseBuilder.getDatabase(this).receitaDao()
        val repository = ReceitaRepository(receitaDao)
        ReceitaViewModelFactory(repository).create(ReceitaViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialize o ViewBinding
        binding = ActivityNovaReceitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSalvarReceita.setOnClickListener {
            salvarReceita()
        }
    }

    private fun salvarReceita() {
        // Obter os dados do formulário
        val nome = binding.etNomeReceita.text.toString()
        val ingredientes = binding.etIngredientes.text.toString()
        val modoPreparo = binding.etModoPreparo.text.toString()
        val tempo = binding.etTempo.text.toString().toIntOrNull()
        val categoria = binding.etCategoria.text.toString() // Supondo que você tenha um campo para categoria

        // Validar os dados
        if (nome.isBlank() || ingredientes.isBlank() || modoPreparo.isBlank() || tempo == null || categoria.isBlank()) {
            Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            return
        }

        // Criar objeto Receita
        val receita = Receita(
            id = 0, // Será gerado automaticamente pelo Room
            nome = nome,
            ingredientes = ingredientes,
            modoPreparo = modoPreparo,
            tempo = tempo,
            categoria = categoria // Passando o valor da categoria
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