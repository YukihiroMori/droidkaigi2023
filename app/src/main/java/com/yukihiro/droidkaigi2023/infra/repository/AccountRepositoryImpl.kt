package com.yukihiro.droidkaigi2023.infra.repository

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.yukihiro.droidkaigi2023.architecture.Secret
import com.yukihiro.droidkaigi2023.architecture.toSecret
import com.yukihiro.droidkaigi2023.domain.mapper.AccountMapper
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.model.AccountId
import com.yukihiro.droidkaigi2023.domain.model.User
import com.yukihiro.droidkaigi2023.domain.repository.account.AccountRepository
import com.yukihiro.droidkaigi2023.infra.api.LoginApi
import com.yukihiro.droidkaigi2023.infra.api.request.mapper.LoginRequestMapper
import com.yukihiro.droidkaigi2023.infra.api.response.LoginResponse
import com.yukihiro.droidkaigi2023.infra.datastore.AccountCacheDataStore
import com.yukihiro.droidkaigi2023.domain.repository.account.exception.AccountException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.withContext
import java.util.Collections.replaceAll
import javax.inject.Inject
import kotlin.jvm.Throws

class AccountRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val accountStore: AccountCacheDataStore,
    private val crashlytics: FirebaseCrashlytics,
) : AccountRepository {

    override fun currentAccount(): Flow<Account> =
        accountStore.getCurrentAccountStream()
            .map { it ?: throw AccountException.NotLoggedIn }

    override suspend fun login(
        email: Secret<String>,
        password: Secret<String>
    ): Account =
        withContext(Dispatchers.IO) {
            val request = LoginRequestMapper.map(email, password)
            val response = loginApi.login(request)

            if (response.data != null) {
                val account = AccountMapper.toModel(response.data.account)
                accountStore.setCurrentAccount(account)
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