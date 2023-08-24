package com.yukihiro.droidkaigi2023.domain.usecase.login

sealed class LoginUseCaseResult {
    object Success : LoginUseCaseResult()

    class Failure(val exception: Throwable) : LoginUseCaseResult()
}