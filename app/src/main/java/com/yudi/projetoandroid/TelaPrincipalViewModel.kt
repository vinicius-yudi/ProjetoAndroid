package com.yudi.projetoandroid

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel

class TelaPrincipalViewModel : ViewModel() {
    fun onGamesClick(view: View) {
        val intent = Intent(view.context, TelaGame::class.java)
        view.context.startActivity(intent)
    }
}
