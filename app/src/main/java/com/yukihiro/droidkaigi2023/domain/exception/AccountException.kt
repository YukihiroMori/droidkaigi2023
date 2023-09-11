package com.yukihiro.droidkaigi2023.domain.exception

sealed class AccountException: Exception() {

    object NotLoggedIn: AccountException(), BusinessException

    object NotRegistered: AccountException(), BusinessException

    object WrongPassword: AccountException(), BusinessException

    object ServerError: AccountException(), BusinessException

    class InternetException(override val cause: Throwable): AccountException(), SystemException

    class IllegalResponseException(override val cause: Throwable): AccountException(), SystemException

    class UnexpectedException(override val cause: Throwable): AccountException(), SystemException
}