package com.yudi.projetoandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private val bd = MeuBancoDeDados
    private val _isDark = MutableLiveData(false)
    val isDark: LiveData<Boolean> get() = _isDark

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> get() = _loginResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var loginJob: Job? = null

    // Alterna o tema (claro/escuro)
    fun toggleTheme() {
        _isDark.value = _isDark.value?.not()
    }

    // Função de login
    fun onLoginClick(username: String, password: String) {
        _loading.value = true

        // Usando coroutines para operações assíncronas
        loginJob = CoroutineScope(Dispatchers.IO).launch {
            val loginSuccess = true
            withContext(Dispatchers.Main) {
                if (loginSuccess) {
                    _loginResult.value = LoginResult(success = true)
                } else {
                    _loginResult.value = LoginResult(success = false, error = "Login ou senha incorretos")
                }

            }
        }
    }

    // Cancela a operação de login se necessário
    fun cancelLoginOperation() {
        loginJob?.cancel()
    }
}

// Classe para encapsular o resultado do login
data class LoginResult(
    val success: Boolean,
    val error: String? = null
)
