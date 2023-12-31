package com.yukihiro.droidkaigi2023.infra.api.login.request.mapper

import com.yukihiro.droidkaigi2023.domain.architecture.Secret
import com.yukihiro.droidkaigi2023.infra.api.login.request.LoginRequest

object LoginRequestMapper {
    fun map(
        email: Secret<String>,
        password: Secret<String>
    ) : LoginRequest = LoginRequest(
        email = email.data,
        password = password.data
    )
}