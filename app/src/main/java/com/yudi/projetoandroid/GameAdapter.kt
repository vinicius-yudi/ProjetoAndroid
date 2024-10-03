package com.yudi.projetoandroid

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(private val cursor: Cursor, private val context: Context, private val userId: Int) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGameName: TextView = itemView.findViewById(R.id.tvGameName)
        val tvGameType: TextView = itemView.findViewById(R.id.tvGameType)
        val btnFavorite: Button = itemView.findViewById(R.id.btnFavoritos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        if (cursor.moveToPosition(position)) {
            val gameName = cursor.getString(cursor.getColumnIndexOrThrow("Nome"))
            val gameType = cursor.getString(cursor.getColumnIndexOrThrow("Tipo"))
            val gameId = cursor.getInt(cursor.getColumnIndexOrThrow("id"))

            holder.tvGameName.text = gameName
            holder.tvGameType.text = gameType

            holder.btnFavorite.setOnClickListener {
                val db = MeuBancoDeDados(context)
                db.inserirFavorito(userId, gameId)
            }
        }
    }

    override fun getItemCount(): Int {
        return cursor.count
    }
}