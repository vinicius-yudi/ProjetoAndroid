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

        val bd = MeuBancoDeDados(this)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val mainLayout = findViewById<ConstraintLayout>(R.id.main)
        val toggleButton = findViewById<Button>(R.id.btn_toggle_color)
        val textView = findViewById<TextView>(R.id.txtTelaCadastro)

        toggleButton.setOnClickListener {
            viewModel.toggleTheme()
        }

        viewModel.isDark.observe(this) { isDark ->
            if (!isDestroyed && !isFinishing) {
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

        binding.btnEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            val res = bd.login(email,senha)

            if (res == 1) {
               Snackbar.make(binding.root, "Login bem-sucedido!", Snackbar.LENGTH_SHORT).show()
                startActivity(Intent(this, TelaPrincipal::class.java))
            } else {
                Snackbar.make(binding.root, "Login Mal Sucessido", Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            if (!isDestroyed && !isFinishing) {
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelLoginOperation()
    }
}
