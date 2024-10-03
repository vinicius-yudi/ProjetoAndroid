package com.yudi.projetoandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TelaGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_game)
        val db = MeuBancoDeDados(this)
    }
}