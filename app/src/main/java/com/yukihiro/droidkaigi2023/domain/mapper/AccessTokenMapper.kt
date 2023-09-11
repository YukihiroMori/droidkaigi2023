package com.yukihiro.droidkaigi2023.domain.mapper

import com.yukihiro.droidkaigi2023.domain.architecture.toSecret
import com.yukihiro.droidkaigi2023.domain.model.AccessToken
import com.yukihiro.droidkaigi2023.infra.api.login.response.LoginResponse

object AccessTokenMapper {

    fun toModel(response: LoginResponse.AccessToken): AccessToken = AccessToken(
        value = response.value.toSecret()
    )
}