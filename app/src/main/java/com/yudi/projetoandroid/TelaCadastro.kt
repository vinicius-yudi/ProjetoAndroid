package com.yudi.projetoandroid

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yudi.projetoandroid.databinding.ActivityTelaCadastroBinding
import com.google.android.material.snackbar.Snackbar

class TelaCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityTelaCadastroBinding
    private val viewModel: TelaCadastroViewModel by viewModels()

    private var isDark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainLayout = binding.main
        val toggleButton = binding.btnToggleColor
        val textView = binding.txtJaCadastrei

        toggleButton.setOnClickListener {
            if (isDark) {
                mainLayout.setBackgroundColor(Color.parseColor("#CCCCCC"))
                textView.setTextColor(Color.BLACK)
            } else {
                mainLayout.setBackgroundColor(Color.parseColor("#252530"))
                textView.setTextColor(Color.WHITE)
            }
            isDark = !isDark
        }

        binding.txtJaCadastrei.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btSalvar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val confirmarSenha = binding.editConfirmarSenha.text.toString()
            val cpf = binding.editCpf.text.toString()
            val telefone = binding.editTelefone.text.toString()

            viewModel.cadastrarUsuario(it, email, senha, confirmarSenha, cpf, telefone)
        }

        viewModel.mensagem.observe(this) { mensagem ->
            Snackbar.make(binding.root, mensagem, Snackbar.LENGTH_SHORT).show()
        }

        viewModel.navegarParaLogin.observe(this) { navegar ->
            if (navegar) {
                val intent = Intent(this, TelaPrincipal::class.java)
                startActivity(intent)
                viewModel.resetarNavegacao()
            }
        }
    }
}
