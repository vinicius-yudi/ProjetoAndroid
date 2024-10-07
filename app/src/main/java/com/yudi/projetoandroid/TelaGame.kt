package com.yudi.projetoandroid

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class TelaGame : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private val db = MeuBancoDeDados(this)
    private var isDark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_game)

        val mainLayout = findViewById<ConstraintLayout>(R.id.main)
        val toggleButton = findViewById<Button>(R.id.btn_toggle_color)

        toggleButton.setOnClickListener {
            if (isDark) {
                mainLayout.setBackgroundColor(Color.parseColor("#CCCCCC"))
            } else {
                mainLayout.setBackgroundColor(Color.BLACK)
            }
            isDark = !isDark
        }

        // Inicializa o Spinner após o setContentView
        spinner = findViewById(R.id.spinnerTelefone)

        findViewById<Button>(R.id.btnSalvar).setOnClickListener {
            val nome = findViewById<Button>(R.id.btnSalvar).text.toString()
            val selectedItem = spinner.selectedItem.toString()
            cria(it, nome, selectedItem)
        }
    }

    private fun cria(view: View, nome: String?, tipo: String?) {
        val insercao = db.inserirJogo(nome, tipo)

        if (insercao != -1L) {
            // Cadastro com sucesso
            val snackbar = Snackbar.make(view, "Jogo cadastrado com sucesso!", Snackbar.LENGTH_SHORT)
            snackbar.show()
        } else {
            // Falha no cadastro
            val snackbar = Snackbar.make(view, "Erro ao cadastrar!", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }
}
