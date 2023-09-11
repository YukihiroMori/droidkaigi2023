package com.yukihiro.droidkaigi2023.domain.model

import com.yukihiro.droidkaigi2023.domain.architecture.Id
import kotlinx.serialization.Serializable

@Serializable
data class AccountId(override val value: String) : Id()