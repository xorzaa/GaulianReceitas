package com.example.galuianreceitas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.galuianreceitas.data.Receita
import com.example.galuianreceitas.data.ReceitaRepository
import kotlinx.coroutines.launch

class ReceitaViewModel(private val repository: ReceitaRepository) : ViewModel() {

    // LiveData para observação das receitas
    val receitas = repository.listarReceitas().asLiveData()

    // LiveData para mostrar o status da API (ex: carregando, erro, sucesso)
    val apiStatus = MutableLiveData<ApiStatus>()

    // Carregar todas as receitas do banco de dados
    fun carregarReceitas() {
        // O LiveData será atualizado automaticamente quando o Flow emitir novos valores
    }

    // Buscar receitas da API com base nos ingredientes fornecidos
    fun buscarReceitasPorIngredientes(ingredientes: String) {
        apiStatus.value = ApiStatus.Loading
        viewModelScope.launch {
            try {
                // Chama o método do repository para buscar as receitas da API
                val listaReceitasApi = repository.buscarReceitasPorIngredientes(ingredientes)

                if (listaReceitasApi != null) {
                    // Atualiza a lista com os resultados da API
                    // Aqui, você pode fazer qualquer lógica adicional, por exemplo, salvar no banco
                    apiStatus.value = ApiStatus.Success(listaReceitasApi)
                } else {
                    apiStatus.value = ApiStatus.Error("Nenhuma receita encontrada")
                }
            } catch (e: Exception) {
                apiStatus.value = ApiStatus.Error("Erro: ${e.message}")
            }
        }
    }

    // Adicionar uma nova receita ao banco de dados
    fun adicionarReceita(receita: Receita) {
        viewModelScope.launch {
            repository.adicionarReceita(receita)
        }
    }

    // Atualizar uma receita existente no banco de dados
    fun atualizarReceita(receita: Receita) {
        viewModelScope.launch {
            repository.atualizarReceita(receita)
        }
    }

    // Excluir uma receita do banco de dados
    fun excluirReceita(receita: Receita) {
        viewModelScope.launch {
            repository.excluirReceita(receita)
        }
    }
}

// Definindo um tipo para o estado da API
sealed class ApiStatus {
    object Loading : ApiStatus() // Quando estiver carregando
    data class Success(val receitas: List<Receita>) : ApiStatus() // Quando tiver sucesso
    data class Error(val message: String) : ApiStatus() // Quando houver erro
}
