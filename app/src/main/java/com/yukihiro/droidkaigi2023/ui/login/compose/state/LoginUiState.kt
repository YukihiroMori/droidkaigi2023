package com.yukihiro.droidkaigi2023.ui.login.compose.state

data class LoginUiState(
    val email: String,
    val password: String,
    val isNotFoundAccount: Boolean,
    val isWrongPassword: Boolean
    ){
    private val hasError = isNotFoundAccount || isWrongPassword
    val isEnableLogin = email.isNotBlank() && password.isNotBlank() && !hasError

    companion object {
        fun default() = LoginUiState(
            email = "",
            password = "",
            isNotFoundAccount = false,
            isWrongPassword = false
        )
    }
}
