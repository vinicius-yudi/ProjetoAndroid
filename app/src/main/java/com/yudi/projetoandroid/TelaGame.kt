package com.yudi.projetoandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class TelaGame : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var viewModel: TelaGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_game)

        viewModel = ViewModelProvider(this).get(TelaGameViewModel::class.java)

        spinner = findViewById(R.id.spinnerTelefone)

        findViewById<Button>(R.id.btnSalvar).setOnClickListener {
            val nome = findViewById<TextInputEditText>(R.id.editJogo).text.toString()
            val selectedItem = spinner.selectedItem.toString()
            viewModel.salvarJogo(it, nome, selectedItem)
        }

        findViewById<Button>(R.id.btnBackToMain).setOnClickListener {
            val intent = Intent(this, TelaPrincipal::class.java)
            startActivity(intent)
        }
    }
}
