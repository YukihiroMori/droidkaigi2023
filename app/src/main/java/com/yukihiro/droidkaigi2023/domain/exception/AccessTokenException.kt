package com.yukihiro.droidkaigi2023.domain.exception

sealed class AccessTokenException : Exception() {
    object NotFoundToken: AccessTokenException(), BusinessException

    class UnexpectedException(override val cause: Throwable): AccountException(), SystemException
}
