package com.yudi.projetoandroid

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar

class TelaCadastroViewModel(application: Application) : AndroidViewModel(application) {

    private val bancoDeDados: MeuBancoDeDados = MeuBancoDeDados(application)

    private val _mensagem = MutableLiveData<String>()
    val mensagem: LiveData<String> get() = _mensagem

    private val _navegarParaLogin = MutableLiveData<Boolean>()
    val navegarParaLogin: LiveData<Boolean> get() = _navegarParaLogin

    fun cadastrarUsuario(view: View, email: String, senha: String, confirmarSenha: String, cpf: String, telefone: String) {
        when {
            email.isEmpty() -> _mensagem.value = "Preencha o E-mail!"
            senha.isEmpty() -> _mensagem.value = "Preencha a Senha!"
            confirmarSenha.isEmpty() -> _mensagem.value = "Confirme a Senha!"
            cpf.isEmpty() -> _mensagem.value = "Preencha o CPF!"
            telefone.isEmpty() -> _mensagem.value = "Preencha o Telefone!"
            !email.contains("@gmail.com") -> _mensagem.value = "E-mail inválido!"
            senha.length <= 5 -> _mensagem.value = "A senha deve ter pelo menos 6 caracteres!"
            confirmarSenha != senha -> _mensagem.value = "As senhas não coincidem!"
            else -> {
                val resultado = bancoDeDados.inserirUsuario(email, email, senha, cpf, telefone)
                if (resultado != -1L) {
                    _mensagem.value = "Cadastro realizado com sucesso!"
                    _navegarParaLogin.value = true
                } else {
                    _mensagem.value = "Erro ao cadastrar!"
                }
            }
        }
    }

    fun resetarNavegacao() {
        _navegarParaLogin.value = false
    }
}
