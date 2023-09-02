package com.yukihiro.droidkaigi2023.infra.usecase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.yukihiro.droidkaigi2023.architecture.Secret
import com.yukihiro.droidkaigi2023.domain.repository.AccountRepository
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCase
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCaseResult
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository,
    private val crashlytics: FirebaseCrashlytics
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
                crashlytics.recordException(it)
                LoginUseCaseResult.Failure(it)
            }
        )
}