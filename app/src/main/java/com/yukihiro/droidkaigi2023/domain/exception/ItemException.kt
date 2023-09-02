package com.yukihiro.droidkaigi2023.domain.exception

sealed class ItemException: DomainException() {

    object NotAuthorized: ItemException()

    object TokenExpired: ItemException()

    object ServerError: ItemException()
}