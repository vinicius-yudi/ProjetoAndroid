package com.yudi.projetoandroid

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(private val cursor: Cursor, private val context: Context, private val userId: Int) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        if (cursor.moveToPosition(position)) {
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("Nome"))
            val tipo = cursor.getString(cursor.getColumnIndexOrThrow("Tipo"))
            holder.bind(nome, tipo)
        }
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeTextView: TextView = itemView.findViewById(R.id.tvGameName)
        private val tipoTextView: TextView = itemView.findViewById(R.id.tvGameType)

        fun bind(nome: String, tipo: String) {
            nomeTextView.text = nome
            tipoTextView.text = tipo
        }
    }
}