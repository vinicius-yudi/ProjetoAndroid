package com.yudi.projetoandroid

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.yudi.projetoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val mainLayout = findViewById<ConstraintLayout>(R.id.main)
        val toggleButton = findViewById<Button>(R.id.btn_toggle_color)
        val textView = findViewById<TextView>(R.id.txtTelaCadastro)

        toggleButton.setOnClickListener {
            viewModel.toggleTheme()
        }

        viewModel.isDark.observe(this) { isDark ->
            if (!isDestroyed && !isFinishing) {  // Verifica o estado da Activity antes de atualizar a UI
                if (isDark) {
                    mainLayout.setBackgroundColor(Color.parseColor("#252530"))
                    textView.setTextColor(Color.WHITE)
                } else {
                    mainLayout.setBackgroundColor(Color.parseColor("#CCCCCC"))
                    textView.setTextColor(Color.BLACK)
                }
            }
        }

        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, TelaCadastro::class.java)
            startActivity(intent)
        }

        window.statusBarColor = Color.parseColor("#FFFFFF")

        // Coletando os dados do email e senha e chamando o método de login do ViewModel
        binding.btnEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            // Chamando o método de login do ViewModel
            viewModel.onLoginClick(email, senha)
        }

        // Observa o resultado do login
        viewModel.loginResult.observe(this) { loginResult ->
            if (!isDestroyed && !isFinishing) {  // Certifique-se de que a Activity ainda está ativa
                if (loginResult.success) {
                    // Login bem-sucedido, redirecionar ou exibir mensagem de sucesso
                    Snackbar.make(binding.root, "Login bem-sucedido!", Snackbar.LENGTH_SHORT).show()
                    startActivity(Intent(this, TelaPrincipal::class.java))
                } else {
                    // Exibe mensagem de erro
                    Snackbar.make(binding.root, loginResult.error ?: "Erro desconhecido", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        // Observa o estado de carregamento
        viewModel.loading.observe(this) { isLoading ->
            if (!isDestroyed && !isFinishing) {  // Verifica o estado da Activity antes de mostrar o ProgressBar
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    // Cancela qualquer tarefa ou operação em segundo plano no onDestroy
    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelLoginOperation()  // Implementar a função de cancelamento no ViewModel
    }
}
