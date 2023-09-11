package com.yukihiro.droidkaigi2023.domain.exception

sealed class ItemException: Exception() {

    object NotAuthorized: ItemException(), BusinessException

    object TokenExpired: ItemException(), BusinessException

    object ServerError: ItemException(), BusinessException

    class InternetException(override val cause: Throwable): ItemException(), SystemException

    class IllegalResponseException(override val cause: Throwable): ItemException(), SystemException

    class DatabaseAccessException(override val cause: Throwable): ItemException(), SystemException

    class UnexpectedException(override val cause: Throwable): ItemException(), SystemException
}