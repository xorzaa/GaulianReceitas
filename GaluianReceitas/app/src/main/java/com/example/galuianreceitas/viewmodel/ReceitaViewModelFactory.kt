package com.example.galuianreceitas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galuianreceitas.data.Receita
import com.example.galuianreceitas.data.ReceitaRepository
import kotlinx.coroutines.launch

class ReceitaViewModel(private val repository: ReceitaRepository) : ViewModel() {

    val receitas = MutableLiveData<List<Receita>>() // Lista observável de receitas

    // Carregar todas as receitas do banco de dados
    fun carregarReceitas() {
        viewModelScope.launch {
            val lista = repository.listarReceitas()
            receitas.postValue(lista) // Atualiza o valor para os observadores
        }
    }

    // Buscar receitas da API com base nos ingredientes fornecidos
    fun buscarReceitasPorIngredientes(ingredientes: String) {
        viewModelScope.launch {
            val listaReceitasApi = repository.buscarReceitasPorIngredientes(ingredientes)
            if (listaReceitasApi != null) {
                receitas.postValue(listaReceitasApi) // Atualiza com os resultados da API
            } else {
                // Lidar com erro, se necessário (por exemplo, exibir mensagem ao usuário)
                receitas.postValue(emptyList()) // Atualiza para uma lista vazia
            }
        }
    }

    // Adicionar uma nova receita ao banco de dados
    fun adicionarReceita(receita: Receita) {
        viewModelScope.launch {
            repository.inserirReceita(receita)
            carregarReceitas() // Atualiza a lista após adicionar
        }
    }

    // Atualizar uma receita existente no banco de dados
    fun atualizarReceita(receita: Receita) {
        viewModelScope.launch {
            repository.atualizarReceita(receita)
            carregarReceitas()
        }
    }

    // Excluir uma receita do banco de dados
    fun excluirReceita(receita: Receita) {
        viewModelScope.launch {
            repository.excluirReceita(receita)
            carregarReceitas()
        }
    }
}