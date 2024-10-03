package com.yudi.projetoandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaPrincipal : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
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

        // Chama a função para mostrar usuários na tela
        mostrarUsuarios()
    }

    private fun mostrarUsuarios() {
        val db = MeuBancoDeDados(this)
        val usuarios = db.obterJogos()

        val usuariosTextView: TextView = findViewById(R.id.usuariosTextView) // Certifique-se de ter esse TextView no layout
        val sb = StringBuilder()

        for (jogo in usuarios) {
            sb.append("Nome: ${jogo.nome}, Tipo: ${jogo.tipo}\n")
        }

        usuariosTextView.text = sb.toString()
    }
}