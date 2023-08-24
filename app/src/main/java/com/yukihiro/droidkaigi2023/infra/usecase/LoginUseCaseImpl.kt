package com.yukihiro.droidkaigi2023.infra.usecase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.yukihiro.droidkaigi2023.architecture.Secret
import com.yukihiro.droidkaigi2023.domain.repository.account.AccountRepository
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCase
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCaseResult
import com.yukihiro.droidkaigi2023.domain.repository.account.exception.AccountException
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
            val account = accountRepository.login(email, password)
            crashlytics.setUserId(account.id.value)
        }.fold(
            onSuccess = { LoginUseCaseResult.Success },
            onFailure = {
                crashlytics.recordException(it)
                LoginUseCaseResult.Failure(it)
            }
        )
}