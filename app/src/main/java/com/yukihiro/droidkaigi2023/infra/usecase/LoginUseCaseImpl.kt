package com.yukihiro.droidkaigi2023.infra.usecase

import com.yukihiro.droidkaigi2023.domain.architecture.Secret
import com.yukihiro.droidkaigi2023.domain.repository.AccessTokenRepository
import com.yukihiro.droidkaigi2023.domain.repository.AccountRepository
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCase
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCaseResult
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accessTokenRepository: AccessTokenRepository,
) : LoginUseCase {

    override suspend operator fun invoke(
        email: Secret<String>,
        password: Secret<String>
    ): LoginUseCaseResult =
        runCatching {
            val token = accountRepository.login(email, password)
            accessTokenRepository.saveToken(token)
        }.fold(
            onSuccess = { LoginUseCaseResult.Success },
            onFailure = { LoginUseCaseResult.Failure(it) }
        )
}