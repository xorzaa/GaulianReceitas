package com.example.galuianreceitas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galuianreceitas.data.Receita
import com.example.galuianreceitas.data.ReceitaRepository
import kotlinx.coroutines.launch

class ReceitaViewModel(private val repository: ReceitaRepository) : ViewModel() {

    val receitas = MutableLiveData<List<Receita>>() // Lista observável de receitas

    // Carregar todas as receitas
    fun carregarReceitas() {
        viewModelScope.launch {
            val lista = repository.listarReceitas()
            receitas.postValue(lista) // Atualiza o valor para os observadores
        }
    }

    // Adicionar uma nova receita
    fun adicionarReceita(receita: Receita) {
        viewModelScope.launch {
            repository.inserirReceita(receita)
            carregarReceitas() // Atualiza a lista após adicionar
        }
    }

    // Atualizar uma receita existente
    fun atualizarReceita(receita: Receita) {
        viewModelScope.launch {
            repository.atualizarReceita(receita)
            carregarReceitas()
        }
    }

    // Excluir uma receita
    fun excluirReceita(receita: Receita) {
        viewModelScope.launch {
            repository.excluirReceita(receita)
            carregarReceitas()
        }
    }
}
