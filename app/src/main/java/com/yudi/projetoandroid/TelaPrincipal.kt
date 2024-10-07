package com.yudi.projetoandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

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

    @SuppressLint("SetTextI18n")
    private fun mostrarJogos() {
        val db = MeuBancoDeDados(this)
        val jogos = db.obterJogos()

        val jogosPorTipo = jogos.groupBy { it.tipo }

        val tiposDeJogosLayout: LinearLayout = findViewById(R.id.tiposDeJogosLayout)
        tiposDeJogosLayout.removeAllViews()

        for ((tipo, jogosDoTipo) in jogosPorTipo) {
            val tipoTextView = TextView(this).apply {
                text = tipo
                textSize = 20f
                setTextColor(Color.WHITE)
                setPadding(16, 16, 16, 16)
            }
            tiposDeJogosLayout.addView(tipoTextView)

            val recyclerView = RecyclerView(this).apply {
                layoutManager = LinearLayoutManager(this@TelaPrincipal, LinearLayoutManager.HORIZONTAL, false)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 16)
                }
            }

            val adapter = CarouselAdapter(jogosDoTipo) { jogo ->
                val result = db.adicionarFavorito(userId, jogo.idJogo)
                if (result != -1L) {
                    Snackbar.make(this@TelaPrincipal.findViewById(android.R.id.content), "Jogo favoritado com sucesso!", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(this@TelaPrincipal.findViewById(android.R.id.content), "Jogo já favoritado!!", Snackbar.LENGTH_SHORT).show()
                }
            }
            recyclerView.adapter = adapter

            tiposDeJogosLayout.addView(recyclerView)
        }
    }

}