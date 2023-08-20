package com.yukihiro.droidkaigi2023.domain.usecase.login

sealed class LoginUseCaseResult {
    object Success : LoginUseCaseResult()

    sealed class Failure : LoginUseCaseResult(){
        object NotRegistered: Failure()

        object WrongPassword: Failure()

        object ServerError: Failure()

        class Unexpected(val exception: Throwable): Failure()
    }
}