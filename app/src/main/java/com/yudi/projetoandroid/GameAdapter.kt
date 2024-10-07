package com.yudi.projetoandroid

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class GameAdapter(private var cursor: Cursor, private val context: Context, private val userId: Int) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        if (cursor.moveToPosition(position)) {
            val jogoId = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Jogo"))
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("Nome"))
            val tipo = cursor.getString(cursor.getColumnIndexOrThrow("Tipo"))
            holder.bind(jogoId, nome, tipo)
        }
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    fun updateCursor(newCursor: Cursor) {
        cursor.close()
        cursor = newCursor
        notifyDataSetChanged()
    }

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeTextView: TextView = itemView.findViewById(R.id.tvGameName)
        private val tipoTextView: TextView = itemView.findViewById(R.id.tvGameType)
        private val removeButton: Button = itemView.findViewById(R.id.btnRemoveFavorite)
        private val favoriteButton: Button = itemView.findViewById(R.id.btnFavorite)

        fun bind(jogoId: Int, nome: String, tipo: String) {
            favoriteButton.visibility = View.GONE
            nomeTextView.text = nome
            tipoTextView.text = tipo

            removeButton.setOnClickListener {
                val db = MeuBancoDeDados(context)
                val result = db.removerFavorito(userId, jogoId)
                if (result > 0) {
                    Snackbar.make(itemView, "Favorito removido com sucesso!", Snackbar.LENGTH_SHORT).show()
                    updateCursor(db.obterFavoritos(userId))
                } else {
                    Snackbar.make(itemView, "Erro ao remover o favorito!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}