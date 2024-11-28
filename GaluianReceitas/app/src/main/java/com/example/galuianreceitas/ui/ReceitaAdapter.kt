package com.seuprojeto.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seuprojeto.R
import com.seuprojeto.data.Receita

class ReceitaAdapter(
    private val receitas: List<Receita>,
    private val onClick: (Receita) -> Unit
) : RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_receita, parent, false)
        return ReceitaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
        val receita = receitas[position]
        holder.tvNome.text = receita.nome
        holder.tvTempo.text = "Tempo: ${receita.tempo} min"
        holder.itemView.setOnClickListener { onClick(receita) }
    }

    override fun getItemCount(): Int = receitas.size

    inner class ReceitaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNomeReceita)
        val tvTempo: TextView = itemView.findViewById(R.id.tvTempoReceita)
    }
}