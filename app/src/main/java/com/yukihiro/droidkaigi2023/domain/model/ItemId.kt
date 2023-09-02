package com.yukihiro.droidkaigi2023.domain.model

import com.yukihiro.droidkaigi2023.architecture.Id
import kotlinx.serialization.Serializable

@Serializable
data class ItemId(override val value: String) : Id()