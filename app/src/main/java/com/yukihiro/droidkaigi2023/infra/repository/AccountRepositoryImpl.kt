package com.yukihiro.droidkaigi2023.infra.repository

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.yukihiro.droidkaigi2023.architecture.Secret
import com.yukihiro.droidkaigi2023.domain.exception.AccountException
import com.yukihiro.droidkaigi2023.domain.mapper.AccountMapper
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.repository.AccountRepository
import com.yukihiro.droidkaigi2023.infra.api.login.LoginApi
import com.yukihiro.droidkaigi2023.infra.api.login.request.mapper.LoginRequestMapper
import com.yukihiro.droidkaigi2023.infra.api.login.response.LoginResponse
import com.yukihiro.droidkaigi2023.infra.preferences.AccountCachePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val accountCache: AccountCachePreferences,
    private val crashlytics: FirebaseCrashlytics
) : AccountRepository {

    override fun getCurrentAccount(): Account =
        accountCache.get() ?: throw AccountException.NotLoggedIn

    override suspend fun login(
        email: Secret<String>,
        password: Secret<String>
    ): Account =
        withContext(Dispatchers.IO) {
            val request = LoginRequestMapper.map(email, password)
            val response = loginApi.login(request)

            if (response.data != null) {
                val account = AccountMapper.toModel(response.data.account)
                accountCache.save(account)
                crashlytics.setUserId(account.id.value)
                account
            } else {
                when (response.errorCode) {
                    LoginResponse.ErrorCode.NotRegistered -> throw AccountException.NotRegistered
                    LoginResponse.ErrorCode.WrongPassword -> throw AccountException.WrongPassword
                    else -> throw AccountException.ServerError
                }
            }
        }
}