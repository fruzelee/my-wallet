package com.crevado.fr.mywallet.login.presentation

data class LoginViewState(
    val isLoading: Boolean = false,
    val data: Any? = null,
    val error: String = ""
)
