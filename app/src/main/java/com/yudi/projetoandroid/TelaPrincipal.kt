package com.yudi.projetoandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class TelaPrincipal : AppCompatActivity() {

    private lateinit var viewModel: TelaPrincipalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)

        viewModel = ViewModelProvider(this).get(TelaPrincipalViewModel::class.java)

        findViewById<Button>(R.id.btnFavoritos).setOnClickListener {
            val intent = Intent(this, Favoritos::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnJogos).setOnClickListener {
            viewModel.onGamesClick(it)
        }
    }
}
