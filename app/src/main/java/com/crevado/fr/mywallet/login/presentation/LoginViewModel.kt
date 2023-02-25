package com.crevado.fr.mywallet.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crevado.fr.mywallet.shared.business.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginViewState())
    val state = _state.asStateFlow()

    fun login(name: String, pin: String) {
        loginUseCase(userName = name, pin = pin).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _state.value = LoginViewState(data = result.data)
                }
                is Result.Error -> {
                    _state.value = LoginViewState(
                        error = result.message ?: "An unexpected error occurred!"
                    )
                }
                is Result.Loading -> {
                    _state.value = LoginViewState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        _state.value = LoginViewState()
    }
}