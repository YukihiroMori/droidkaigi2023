package com.yukihiro.droidkaigi2023.domain.usecase.login

import com.yukihiro.droidkaigi2023.domain.architecture.Secret

interface LoginUseCase {
    suspend operator fun invoke(
        email: Secret<String>,
        password: Secret<String>
    ): LoginUseCaseResult
}