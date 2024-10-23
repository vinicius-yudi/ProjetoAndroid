package com.yudi.projetoandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TelaPrincipalViewModel(application: Application) : AndroidViewModel(application) {

    private val bd = MeuBancoDeDados(application)

    private val _jogosPorCategoria = MutableLiveData<Map<String, List<Jogo>>>()
    val jogosPorCategoria: LiveData<Map<String, List<Jogo>>> get() = _jogosPorCategoria

    fun carregarJogos() {
        CoroutineScope(Dispatchers.IO).launch {
            val jogos = bd.obterJogos()
            val jogosOrganizados = jogos.groupBy { it.tipo }
            withContext(Dispatchers.Main) {
                _jogosPorCategoria.value = jogosOrganizados
            }
        }
    }
}
