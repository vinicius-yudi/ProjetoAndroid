package com.yudi.projetoandroid

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaPrincipal : AppCompatActivity() {

    private var userId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_principal)

        window.statusBarColor = Color.parseColor("#00a86b")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnJogos).setOnClickListener {
            val intent = Intent(this, TelaGame::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnFavoritos).setOnClickListener {
            val intent = Intent(this, Favoritos::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }

        mostrarJogos()
    }

    private fun mostrarJogos() {
        val db = MeuBancoDeDados(this)
        val jogos = db.obterJogos()

        val jogosTextView: TextView = findViewById(R.id.jogosTextView)
        val sb = StringBuilder()

        for (jogo in jogos) {
            sb.append("Nome: ${jogo.nome}, Tipo: ${jogo.tipo}\n")
        }

        jogosTextView.text = sb.toString()
    }
}