package com.yukihiro.droidkaigi2023.infra.api.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
