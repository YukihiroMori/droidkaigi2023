package com.yukihiro.droidkaigi2023.infra.repository

import com.yukihiro.droidkaigi2023.domain.exception.AccessTokenException
import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import com.yukihiro.droidkaigi2023.domain.repository.AccessTokenRepository
import com.yukihiro.droidkaigi2023.infra.preferences.AccessTokenCachePreferences
import javax.inject.Inject

class AccessTokenRepositoryImpl @Inject constructor(
    private val accessTokenCachePreferences: AccessTokenCachePreferences
): AccessTokenRepository {
    override fun saveToken(token: AccessToken) {
        accessTokenCachePreferences.save(token)
    }

    override fun getToken(): AccessToken =
        accessTokenCachePreferences.get() ?: throw AccessTokenException.NotFoundToken

    override fun clearToken() {
        accessTokenCachePreferences.clear()
    }
}