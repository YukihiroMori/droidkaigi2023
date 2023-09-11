package com.yukihiro.droidkaigi2023.domain.architecture

import kotlinx.serialization.Serializable

inline fun <reified T> T.toSecret() = Secret(this)

@Serializable
class Secret<T>(val data: T){
    override fun equals(other: Any?): Boolean {
        if(other !is Secret<*>) return false
        return data == other.data
    }

    override fun hashCode(): Int {
        return data?.hashCode() ?: 0
    }

    override fun toString(): String {
        return ""
    }
}