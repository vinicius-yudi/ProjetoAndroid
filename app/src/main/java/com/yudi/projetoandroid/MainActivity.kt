package com.yudi.projetoandroid

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.yudi.projetoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isDark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val mainLayout = findViewById<ConstraintLayout>(R.id.main)
        val toggleButton = findViewById<Button>(R.id.btn_toggle_color)
        val textView = findViewById<TextView>(R.id.txtTelaCadastro)

        toggleButton.setOnClickListener {
            if (isDark) {
                mainLayout.setBackgroundColor(Color.parseColor("#CCCCCC"))
                textView.setTextColor(Color.BLACK)
            } else {
                mainLayout.setBackgroundColor(Color.BLACK)
                textView.setTextColor(Color.WHITE)
            }
            isDark = !isDark
        }

        window.statusBarColor = Color.parseColor("#FFFFFF")

        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, TelaCadastro::class.java)
            startActivity(intent)
        }

        // Lógica para o botão Entrar
        binding.btnEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            when {
                email.isEmpty() -> {
                    binding.editEmail.error = "Preencha o E-mail!"
                }
                senha.isEmpty() -> {
                    binding.editSenha.error = "Preencha a Senha!"
                }
                !email.contains("@gmail.com") -> {
                    val snackbar = Snackbar.make(it, "E-mail inválido!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                else -> {
                    login(it)
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun usuarios(){
        val bancoDeDados = MeuBancoDeDados(this)
        val cursor = bancoDeDados.obterUsuarios()
        val listaUsuarios = StringBuilder()
        while (cursor.moveToNext()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val userEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            listaUsuarios.append("ID: $userId, Email: $userEmail\n")
        }
        cursor.close()
        binding.txtListaUsuarios.text = listaUsuarios.toString()
    }

    private fun login(view: View) {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        binding.btnEntrar.isEnabled = false
        binding.btnEntrar.setTextColor(Color.parseColor("#FFFFFF"))

        when {
            email.isEmpty() -> {
                binding.editEmail.error = "Preencha o E-mail!"
                binding.btnEntrar.isEnabled = true
                return
            }
            senha.isEmpty() -> {
                binding.editSenha.error = "Preencha a Senha!"
                binding.btnEntrar.isEnabled = true
                return
            }
            else -> {
                val bancoDeDados = MeuBancoDeDados(this)
                val loginValido = bancoDeDados.verificarLogin(email, senha)

                if (loginValido) {
                    navegarTelaPrincipal(email)
                    val snackbar = Snackbar.make(view, "Login realizado com sucesso!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                } else {
                    binding.btnEntrar.isEnabled = true
                    val snackbar = Snackbar.make(view, "E-mail ou senha incorretos!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
            }
        }
    }

    private fun navegarTelaPrincipal(email: String) {
        val intent = Intent(this, TelaPrincipal::class.java)
        intent.putExtra("USER_EMAIL", email)
        startActivity(intent)
    }
}