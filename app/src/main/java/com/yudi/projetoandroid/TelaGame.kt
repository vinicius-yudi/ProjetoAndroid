package com.yudi.projetoandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class TelaGame : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private val db = MeuBancoDeDados(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_game)

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
