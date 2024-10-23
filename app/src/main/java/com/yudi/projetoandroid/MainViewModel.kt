package com.yudi.projetoandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private val _isDark = MutableLiveData(false)
    val isDark: LiveData<Boolean> get() = _isDark

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var loginJob: Job? = null

    fun toggleTheme() {
        _isDark.value = _isDark.value?.not()
    }

    fun cancelLoginOperation() {
        loginJob?.cancel()
    }
}

data class LoginResult(
    val success: Boolean,
    val error: String? = null
)
