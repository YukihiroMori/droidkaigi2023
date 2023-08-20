package com.yukihiro.droidkaigi2023.infra.usecase

import com.yukihiro.droidkaigi2023.architecture.Secret
import com.yukihiro.droidkaigi2023.domain.repository.account.AccountRepository
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCase
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCaseResult
import com.yukihiro.droidkaigi2023.infra.repository.exception.AccountException
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : LoginUseCase {

    override suspend operator fun invoke(
        email: Secret<String>,
        password: Secret<String>
    ): LoginUseCaseResult =
        runCatching {
            accountRepository.login(email, password)
        }.fold(
            onSuccess = { LoginUseCaseResult.Success },
            onFailure = {
                when (it) {
                    is AccountException.NotRegistered -> LoginUseCaseResult.Failure.NotRegistered
                    is AccountException.WrongPassword -> LoginUseCaseResult.Failure.WrongPassword
                    is AccountException.ServerError -> LoginUseCaseResult.Failure.ServerError
                    else -> LoginUseCaseResult.Failure.Unexpected(it)
                }
            }
        )
}