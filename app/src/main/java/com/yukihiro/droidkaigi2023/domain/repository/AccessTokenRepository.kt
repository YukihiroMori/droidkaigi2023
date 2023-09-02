package com.yukihiro.droidkaigi2023.domain.repository

import com.yukihiro.droidkaigi2023.domain.exception.AccessTokenException
import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import kotlin.jvm.Throws

interface AccessTokenRepository {
    fun saveToken(token: AccessToken)

    @Throws(AccessTokenException.NotFoundToken::class)
    fun getToken(): AccessToken

    fun clearToken()
}