package com.yukihiro.droidkaigi2023.domain.mapper

import com.yukihiro.droidkaigi2023.architecture.toSecret
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.model.AccountId
import com.yukihiro.droidkaigi2023.domain.model.User
import com.yukihiro.droidkaigi2023.domain.model.UserId
import com.yukihiro.droidkaigi2023.infra.api.response.LoginResponse

object AccountMapper {
    fun toModel(response: LoginResponse.Account): Account = Account(
        id = AccountId(response.id),
        user = User(
            id = UserId(response.user.id),
            name = response.user.name
        ),
        email = response.email.toSecret(),
        password = response.password.toSecret()
    )
}