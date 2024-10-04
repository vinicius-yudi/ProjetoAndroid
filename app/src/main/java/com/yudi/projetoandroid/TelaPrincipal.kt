package com.yudi.projetoandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        val jogosTextView: LinearLayout = findViewById(R.id.jogosTextView)
        jogosTextView.removeAllViews()

        for (jogo in jogos) {
            val jogoLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 16)
                }
                setBackgroundResource(R.drawable.rounded_background)
                setPadding(16, 16, 16, 16)
            }

            val gameNameTextView = TextView(this).apply {
                text = "${jogo.nome}"
                textSize = 18f
                setTextColor(Color.BLACK)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }

            val gameTypeTextView = TextView(this).apply {
                text = "${jogo.tipo}"
                textSize = 14f
                setTextColor(Color.BLACK)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER_HORIZONTAL
                    topMargin = 8
                }
            }

            val favoriteButton = Button(this).apply {
                text = "Favoritar"
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER_HORIZONTAL
                    topMargin = 8
                }
                setOnClickListener {
                    val result = db.adicionarFavorito(userId, jogo.idJogo)
                    if (result != -1L) {
                        Snackbar.make(this@TelaPrincipal.findViewById(android.R.id.content), "Jogo favoritado com sucesso!", Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(this@TelaPrincipal.findViewById(android.R.id.content), "Erro ao favoritar o jogo!", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }

            jogoLayout.addView(gameNameTextView)
            jogoLayout.addView(gameTypeTextView)
            jogoLayout.addView(favoriteButton)

            jogosTextView.addView(jogoLayout)
        }
    }
}