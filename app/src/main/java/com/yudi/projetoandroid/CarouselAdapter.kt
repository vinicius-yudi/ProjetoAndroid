package com.yudi.projetoandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CarouselAdapter(private val jogos: List<Jogo>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameNameTextView: TextView = itemView.findViewById(R.id.tvGameName)
        val gameTypeTextView: TextView = itemView.findViewById(R.id.tvGameType)
        val favoriteButton: Button = itemView.findViewById(R.id.btnFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val jogo = jogos[position]
        holder.gameNameTextView.text = jogo.nome
        holder.gameTypeTextView.text = jogo.tipo

        holder.favoriteButton.setOnClickListener {
            val db = MeuBancoDeDados(holder.itemView.context)
            val favo = db.obterFav(jogo.idJogo)
            if ( favo.count == 0 ) {
                holder.itemView.animate().alpha(0f).setDuration(300).withEndAction {
                    db.adicionarFavorito(1, jogo.idJogo)
                    com.google.android.material.snackbar.Snackbar.make(holder.itemView, "Jogo favoritado com sucesso!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).show()
                    holder.itemView.animate().alpha(1f).setDuration(300).start()
                }.start()
            } else {
                com.google.android.material.snackbar.Snackbar.make(holder.itemView, "Jogo já favoritado!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = jogos.size
}