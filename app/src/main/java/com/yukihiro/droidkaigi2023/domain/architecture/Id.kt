package com.yukihiro.droidkaigi2023.domain.architecture

import kotlinx.serialization.Serializable


@Serializable
abstract class Id {
    abstract val value: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Id) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}