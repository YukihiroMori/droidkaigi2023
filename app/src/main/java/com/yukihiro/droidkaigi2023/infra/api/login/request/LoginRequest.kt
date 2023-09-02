package com.yukihiro.droidkaigi2023.infra.api.login.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
