package com.yukihiro.droidkaigi2023.domain.model

import com.yukihiro.droidkaigi2023.architecture.Entity
import com.yukihiro.droidkaigi2023.architecture.Secret
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Account(
    override val id: AccountId = AccountId(value = UUID.randomUUID().toString()),
    val user: User,
    val email: Secret<String>,
    val password: Secret<String>,
) : Entity<AccountId>
