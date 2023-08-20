package com.yukihiro.droidkaigi2023.ui.login.compose.listener


import com.yukihiro.droidkaigi2023.ui.error.compose.listener.ErrorListener

interface LoginListener: ErrorListener {
    fun onEditEmail(email: String)
    fun onEditPassword(password: String)
    fun onClickLogin()
}