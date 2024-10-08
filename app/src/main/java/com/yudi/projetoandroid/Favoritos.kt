package com.yudi.projetoandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Favoritos : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        val userId = intent.getIntExtra("USER_ID", -1)
        val db = MeuBancoDeDados(this)
        val cursor = db.obterFavoritos(userId)

        val rvFavoritos: RecyclerView = findViewById(R.id.rvFavoritos)
        val tvNoFavorites: TextView = findViewById(R.id.tvNoFavorites)
        val btnBackToGames: Button = findViewById(R.id.btnBackToGames)
        val backToMain: Button = findViewById(R.id.btnBackToMain)

        if (cursor.count == 0) {
            rvFavoritos.visibility = View.GONE
            backToMain.visibility = View.GONE
            tvNoFavorites.visibility = View.VISIBLE
            btnBackToGames.visibility = View.VISIBLE
        } else {
            rvFavoritos.layoutManager = GridLayoutManager(this, 2) // 2 colunas
            val adapter = GameAdapter(cursor, this, userId)
            rvFavoritos.adapter = adapter
        }

        findViewById<Button>(R.id.btnBackToMain).setOnClickListener {
            val intent = Intent(this, TelaPrincipal::class.java)
            startActivity(intent)
        }

        btnBackToGames.setOnClickListener {
            val intent = Intent(this, TelaPrincipal::class.java)
            startActivity(intent)
        }
    }
}