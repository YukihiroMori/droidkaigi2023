package com.yukihiro.droidkaigi2023.domain.model

import com.yukihiro.droidkaigi2023.domain.architecture.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    override val id: ItemId,
    val name: String,
    val image: String,
) : Entity<ItemId>