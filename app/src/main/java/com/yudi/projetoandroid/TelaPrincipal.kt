package com.yudi.projetoandroid

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaPrincipal : AppCompatActivity() {
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

        // Chama a função para mostrar usuários na tela
        mostrarUsuarios()
    }

    private fun mostrarUsuarios() {
        val db = MeuBancoDeDados(this)
        val usuarios = db.obterUsuarios()

        val usuariosTextView: TextView = findViewById(R.id.usuariosTextView) // Certifique-se de ter esse TextView no layout
        val sb = StringBuilder()

        for (usuario in usuarios) {
            sb.append("Nome: ${usuario.nome}, Email: ${usuario.email}, Telefone: ${usuario.telefone}\n")
        }

        usuariosTextView.text = sb.toString()
    }
}