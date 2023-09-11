package com.yukihiro.droidkaigi2023.domain.model

import com.yukihiro.droidkaigi2023.domain.architecture.Entity
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class User(
    override val id: UserId = UserId(UUID.randomUUID().toString()),
    val name: String
) : Entity<UserId>