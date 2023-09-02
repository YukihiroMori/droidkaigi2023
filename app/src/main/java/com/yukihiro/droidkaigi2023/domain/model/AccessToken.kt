package com.yukihiro.droidkaigi2023.domain.model

import com.yukihiro.droidkaigi2023.architecture.Secret
import kotlinx.serialization.Serializable

@Serializable
data class AccessToken(val value : Secret<String>)
