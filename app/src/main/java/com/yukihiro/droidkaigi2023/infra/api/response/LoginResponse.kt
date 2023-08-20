package com.yukihiro.droidkaigi2023.infra.api.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val data: Data?,
    val errorCode: Int?,
){
    @Serializable
    data class Data(
        val account: Account
    )

    @Serializable
    data class Account(
        val id: String,
        val user: User,
        val email: String,
        val password: String
    )

    @Serializable
    data class User(
        val id: String,
        val name: String
    )

    object ErrorCode{
        val NotRegistered = 301
        val WrongPassword = 302
    }
}
