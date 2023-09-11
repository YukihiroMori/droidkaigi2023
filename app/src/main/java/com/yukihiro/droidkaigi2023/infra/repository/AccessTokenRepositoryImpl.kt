package com.yukihiro.droidkaigi2023.infra.repository

import com.yukihiro.droidkaigi2023.domain.exception.AccessTokenException
import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import com.yukihiro.droidkaigi2023.domain.repository.AccessTokenRepository
import com.yukihiro.droidkaigi2023.infra.preferences.AccessTokenCachePreferences
import javax.inject.Inject

class AccessTokenRepositoryImpl @Inject constructor(
    private val accessTokenCachePreferences: AccessTokenCachePreferences
) : AccessTokenRepository {
    override fun saveToken(token: AccessToken) {
        runCatching { accessTokenCachePreferences.save(token) }
            .fold(
                onSuccess = {  },
                onFailure = { throw AccessTokenException.UnexpectedException(it) }
            )
    }

    override fun getToken(): AccessToken =
        runCatching {
            accessTokenCachePreferences.get()
        }.fold(
            onSuccess = { it ?: throw AccessTokenException.NotFoundToken },
            onFailure = { throw AccessTokenException.UnexpectedException(it) }
        )

    override fun clearToken() =
        runCatching {
            accessTokenCachePreferences.clear()
        }.fold(
            onSuccess = {  },
            onFailure = { throw AccessTokenException.UnexpectedException(it) }
        )
}