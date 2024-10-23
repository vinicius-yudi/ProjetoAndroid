package com.yudi.projetoandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CarouselAdapter(private val jogos: List<Jogo>, private val onFavoritarClick: (Jogo) -> Unit) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameNameTextView: TextView = itemView.findViewById(R.id.tvGameName)
        val gameTypeTextView: TextView = itemView.findViewById(R.id.tvGameType)
        val favoriteButton: Button = itemView.findViewById(R.id.btnFavoritar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val jogo = jogos[position]
        holder.gameNameTextView.text = jogo.nome
        holder.gameTypeTextView.text = jogo.tipo

        holder.favoriteButton.setOnClickListener {
            onFavoritarClick(jogo)
        }
    }

    override fun getItemCount(): Int = jogos.size
}