package com.example.galuianreceitas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galuianreceitas.data.DatabaseBuilder
import com.example.galuianreceitas.data.ReceitaRepository
import com.example.galuianreceitas.viewmodel.ReceitaViewModel
import com.example.galuianreceitas.viewmodel.ReceitaViewModelFactory
import com.example.galuianreceitas.ui.ReceitaAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: ReceitaViewModel by viewModels {
        ReceitaViewModelFactory(ReceitaRepository(DatabaseBuilder.getDatabase(this).receitaDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val searchButton: Button = findViewById(R.id.search_button)
        val ingredientsInput: EditText = findViewById(R.id.ingredients_input)

        val adapter = ReceitaAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.receitas.observe(this, Observer { receitas ->
            adapter.receitas = receitas
            adapter.notifyDataSetChanged()
        })

        searchButton.setOnClickListener {
            val ingredientes = ingredientsInput.text.toString()
            if (ingredientes.isNotEmpty()) {
                viewModel.buscarReceitasPorIngredientes(ingredientes)
            }
        }
    }
}