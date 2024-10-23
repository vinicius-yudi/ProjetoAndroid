package com.yudi.projetoandroid

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar

class TelaGameViewModel(context: Context) : ViewModel() {

    private val db = MeuBancoDeDados(context)

    fun salvarJogo(view: View, nome: String?, tipo: String?) {
        val insercao = db.inserirJogo(nome, tipo)
        val message = if (insercao != -1L) "Jogo cadastrado com sucesso!" else "Erro ao cadastrar!"
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}
